from flask import request, redirect, render_template, session, flash
import cgi
from app import app, db
from models import User, Activity, Teacher

teacher_list = ['teacher','teacher1','teacher2','teacher3']
#def get_goal_list():
#    return Activity.query.get.all()


#@app.before_request
#def require_login():
#    allowed_routes = ['t_login', 'p_login', 'signup', 'blog', 'index']
#    if request.endpoint not in allowed_routes and 'username' not in session:
#        return redirect('/')



@app.route('/', methods=['POST','GET'])
def index():
    return render_template('welcome_screen.html')

@app.route('/t_login', methods = ['POST','GET'])
def t_login():
    return render_template('t_login.html')

@app.route('/p_login', methods = ['POST', 'GET'])
def p_login():
    return render_template('p_login.html')




@app.route('/teacher_login',methods=["POST","GET"])
def teacher_login():
    if request.method=='POST':
        username = request.form['username']
        password = request.form['password']
        verify=request.form['verify']
        existing_teacher=Teacher.query.filter_by(username=username).first()

        if not existing_teacher:
            flash("No such account - please see administrator to create account")
            return redirect ('/')
        
        if username not in teacher_list:
            flash("Access denied. Please see administrator.")
            return redirect ('/')

        existing_pw=existing_teacher.password
           
        if existing_teacher and password != existing_pw:
            flash("Invalid password.")
            return redirect('/t_login')

        if verify != password:
            flash('Passwords do not match.')
            return redirect('/t_login')

        if 'username' in session:
            flash("User already logged in!")
            return redirect('/t_login')
            
        if verify == password and password==existing_pw and 'username' not in session:
            session['username'] = username
            return redirect ('/add_activity')

    else:
        return render_template('welcome_screen.html')


@app.route("/parent_login", methods=['GET', 'POST'])
def parent_login():
    if request.method=='POST':
        username = request.form['username']
        password = request.form['password']
        verify=request.form['verify']
        existing_user=User.query.filter_by(username=username).first()

        if username in teacher_list:
            flash("Please log in at Teacher Portal.")
            return redirect ('/t_login')

        if not existing_user:
            flash("No such user - please create account")
            return render_template('signup.html')

        
        existing_pw=existing_user.password
           
        if existing_user and password != existing_pw:
            flash("Invalid password.")
            return render_template('p_login.html')

        if verify != password:
            flash('Passwords do not match.')
            return render_template('p_login.html')

        if 'username' in session:
            flash("User already logged in!")
            return render_template('p_login.html')
            

        if verify == password and password==existing_pw and 'username' not in session:
            session['username'] = username
            return redirect ('/family_page')

    else:
        return render_template('welcome_screen.html')

@app.route('/add_activity', methods = ["GET", "POST"])
def add_activity():
    if request.method=="GET":
        return render_template ('add_activity.html')

    name_error=''
 
    author = Teacher.query.filter_by(username=session['username']).first()
    
    completed = False

    if request.method=="POST":
        activity_name=request.form['name']
        learning_goal=request.form['goal']
        for_age=request.form['forage']
        location=request.form['where']
        today=request.form['date']
        lesson_plan=request.form['lessonplan']
        point_value=request.form['points']
        link_to=request.form['link']


    if len(activity_name) < 1:
        name_error ="Please enter an activity name."

    if not name_error:
        new_activity=Activity(name=activity_name,completed=completed,goal=learning_goal,forage=for_age,dateadded=today,lessonplan=lesson_plan,points=point_value,link=link_to,author=author,where=location)

        db.session.add(new_activity)
        db.session.commit()
        return render_template ('activity_entry.html')

        return render_template('add_activity.html', name_error=name_error)


@app.route('/family_page',methods = ["POST","GET"])

def all_list():
    owner=User.query.filter_by(username=session['username']).first()
    activities=Activity.query.all()
    return render_template('family_page.html',activities=activities, owner=owner)
   # return render_template ('family_list.html')

@app.route('/this_week_list', methods = ["POST","GET"])
def this_week_list():
    owner=User.query.filter_by(username=session['username']).first()
    new_activities=Activity.query.filter_by(dateadded = '2017-10-27').all()
    return render_template('this_week_list.html',activities=new_activities, owner=owner)    

@app.route('/activity_detail',methods= ["POST","GET"])
def activity_detail():
    owner=User.query.filter_by(username=session['username']).first()
    activities=Activity.query.all()
    return render_template('activity_detail.html', activities=activities)

@app.route("/signup", methods=['GET', 'POST'])
def signup():
    if request.method == 'POST':
        username = request.form['username']
        password = request.form['password']
        verify=request.form['verify']
        email=['email']
        mobile=['mobile']

            
        if verify != password:
            flash('Passwords do not match.')
            return render_template('signup.html')

        if len(username) <= 3:
            flash("Username must have at least four characters.")
            return render_template('signup.html')

        if len(password) <= 3 or len(password) >12:
            flash("Password must have from 4 to 12 characters.")
            return render_template('signup.html')

        existing_user=User.query.filter_by(username=username).first()
        if not existing_user:
            new_user = User(email, username, password,mobile)
            db.session.add(new_user)
            db.session.commit()
            session['username'] = username
            return redirect("/family_page")

        else:
            flash("Username exists")
            return render_template('signup.html')

    else:
        return render_template('signup.html')

#@app.route("/add", methods=["POST"])
#def add_to_list():
#    activity_id=request.form['activity_id']
#    activity_owner = User.query.filter_by(username=session['username']).first()
#    
#    activity=Activity.query.get(activity_id)
#    owner=activity_owner
#    db.session.add(owner)
#    db.session.commit()
#    return render_template ('family_list.html')

## family_list IS NEW-- RELATED TO MAKING LIST OF TO DO AND COMPLETED

@app.route("/family_list", methods=['POST','GET'])
def activity_list():
    owner=User.query.filter_by(username=session['username']).first()
    
    if request.method == "POST":
        
        activity_id=(request.form['activity-id'])
        activity=Activity.query.get(activity_id)
        activity.owner=owner

      #  activity_name=request.form['activity']
      #  new_activity=Activity(activity_name)
        db.session.add(activity)
        db.session.commit()

   # activities= Activity.query.filter_by(completed=False, owner=owner).all()
        completed_activities=Activity.query.filter_by(completed=True,owner=owner).all()

        return render_template('family_list.html')

@app.route('/logout')
def logout():
    del session['username']
    return redirect('/')


app.secret_key = 'supersecretkey'


if __name__ == '__main__':
    app.run()