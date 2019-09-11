from flask import Flask
from flask import make_response
import json
import urllib
#from class import *

# Application instance
app = Flask(__name__)

@app.route('/', methods = ['GET'])
def root():
    with open('help.html') as f:
        read_data = f.read()
    f.close
    return read_data

@app.errorhandler(404)
def not_found(error):
    return make_response(jsonify({'error': 'Not found'}), 404)

@app.route('/stats_request', methods = ['GET'])
def statsRequest():
    url = "http://127.0.0.1:8080/data_request"
    d = urllib.request.urlopen(url).read()
    return d



if __name__ == '__main__':
    app.run()

