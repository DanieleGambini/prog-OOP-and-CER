from flask import Flask
from flask import make_response
from flask import json
import json
import urllib
import sys
#from dataClass import Data

#def datasetConstructor():
  #  dataset = Data(1,2,3,4,5)
    #return dataset

# Application instance
app = Flask(__name__)

@app.route('/stats', methods=['GET'])
def stats():
    geo = 'geadsfIT'
    obj= '4'
    return {"GEO": geo, "OBJ": obj }

@app.route('/start', methods = ['GET'])
def start():
    with open('start.html') as start:
        read_start = start.read()
    start.close
    return read_start

@app.route('/help', methods = ['GET'])
def help():
    with open('help.html') as fhelp:
        read_help = fhelp.read()
    fhelp.close
    return read_help

@app.errorhandler(404)
def not_found(error):
    return make_response(jsonify({'error': 'Not found'}), 404)

@app.route('/data', methods = ['GET'])
def statsRequest():
    url = "http://127.0.0.1:8080/data"
    data = urllib.request.urlopen(url).read()
    #response = app.response_class(response=json.bumps(data), status=200, mimetype='application/json')
    #return flask.jsonify(data)
    return data



if __name__ == '__main__':
    app.run()

