import logging
import json
from Stats import *

import azure.functions as func

# "route": "stats/{geo:length(2):alpha}.{obj:length(3,5)}/{filter}",

def main(req: func.HttpRequest) -> str:
    logging.info('Submit the dataset as a POST')
    
    url_params1 = req.params.get('name')
    url_params2 = req.params.get('surname')
    reviced_body = req.get_json()
    logging.info(url_params1 + url_params2)
    logging.info(reviced_body)
    


    #stringa = Parser()
    json

    return json.dumps(reviced_body)
    #return func.HttpResponse(f"{stringa}")
    #return json.dumps({'name': input.name,'length': input.length,'content': input.read().decode('utf-8')})
    
    
    
    '''
    print(name)
    if not name: #se name==0
        try:
            req_body = body.get_json() #se non ci sono parametri prendi dal body 
        except ValueError:
            pass
        else:
            name = req_body.get('name')

    if name:
        return func.HttpResponse(f"Hello {name}!")
    else:
        return func.HttpResponse(
             "Please pass a name on the query string or in the request body",
             status_code=400
        )
'''