from flask import Flask, request, redirect, render_template
import cgi
import os




#boilerplate language to call templates


app = Flask(__name__)
app.config['DEBUG'] = True





@app.route("/")
def index():
    return render_template('hello_form.html')

@app.route("/hello", methods=["POST"])
def hello():
    first_name = request.form['first_name']
    return render_template('hello_greeting.html', name = first_name)
       
       



@app.route('/validate-time')
def display_time_form():
    return render_template('time_form.html')
# with jinja templates you don't have to put in strings, what to pass in.

def is_integer(num):
    try:
        int(num)
        return True
    except ValueError:
        return False

@app.route('/validate-time', methods = ['POST'])
def validate_time():
    hours = request.form['hours']
    minutes = request.form['minutes']

    hours_error=''
    minutes_error=''

    if not is_integer(hours):
        hours_error = 'Not a valid integer'
        hours=''
    else:
        hours = int(hours)
        if hours > 23 or hours < 0:
            hours_error = 'Hour value out of range (0-23)'
            hours = ''

    if not is_integer(minutes):
        minutes_error = 'Not a valid integer'
        minutes = ''
    else:
        minutes=int(minutes)
        if minutes >59 or minutes < 0:
            minutes_error = 'Minutes value out of range (0-59)'
            minutes = ''    #all the resets keep the invalid values from being reinserted into the form


    if not minutes_error and not hours_error:  #that's why we had to initiate them as empty
        time = str(hours)+':' + str(minutes)
        return redirect('/valid-time?time={0}'.format(time)) #this redirects to the page in the app.route
                                        #you have to import redirect above
    else:
        return render_template('time_form.html', hours_error=hours_error, 
            minutes_error=minutes_error, hours=hours, minutes= minutes)

@app.route('/valid-time')
def valid_time():
    time = request.args.get('time')
    return '<h1>You submitted {0}. Thanks for submitting a valid time!</h1>'.format(time)
        #this took the validated time entry and inserted it into the redirected page.

tasks = []


@app.route('/todos', methods = ['POST', 'GET'])
def todos():

    if request.method == 'POST':        # this tells the app, if something is put in, what to do
        task = request.form['task']
        tasks.append(task)

    return render_template('todos.html', title = "TODOs", tasks=tasks)
        # this tells the base to use that title

app.run()
