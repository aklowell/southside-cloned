from app import db

class Teacher(db.Model):
    id = db.Column(db.Integer, primary_key=True)
    email = db.Column(db.String(120), unique=True)
    username = db.Column(db.String(120), unique=True)
    password = db.Column(db.String(12))
    lessons = db.relationship('Activity',backref='author')

    def __init__(self,username,password):
        self.username=username
        self.password=password

class User(db.Model):
    id = db.Column(db.Integer, primary_key=True)
    email = db.Column(db.String(120), unique=True)
    username = db.Column(db.String(120), unique=True)
    password = db.Column(db.String(12))
    mobile = db.Column(db.String(12))
    activities = db.relationship('Activity', backref='owner')

    
    def __init__(self, email, username, password, mobile):
        self.email = email
        self.password = password
        self.username = username
        self.mobile=mobile
    
    def __repr__(self):
        return '<User %r>' % self.username

class Activity(db.Model):
    id = db.Column(db.Integer, primary_key=True)
    name = db.Column(db.String(120))
    completed = db.Column(db.Boolean)
    goal = db.Column(db.String(120))
    forage = db.Column(db.String(10))
    where = db.Column(db.String(120))
    dateadded = db.Column(db.Date)
    lessonplan = db.Column(db.String(120))
    points = db.Column(db.Integer)
    link = db.Column(db.String(256))
    owner_id = db.Column(db.Integer, db.ForeignKey('user.id'))
    author_id = db.Column(db.Integer, db.ForeignKey('teacher.id'))

    def __init__(self, name, completed,goal,forage,where,dateadded,lessonplan,points,link,author):
        self.name = name
        self.completed=False
        self.goal=goal
        self.forage = forage
        self.where = where
        self.dateadded=dateadded
        self.lessonplan = lessonplan
        self.points = points
        self.link = link
        self.author=author
        

    def __repr__(self):
        return '<Activity %r>' % self.name
