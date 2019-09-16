import logging
import json
#from Stats import *

import azure.functions as func

# "route": "stats/{geo:length(2):alpha}.{obj:length(3,5)}/{filter}",

def main(req: func.HttpRequest) -> str:
    logging.info('Submit the dataset as a POST')
    
    geo = req.params.get('GEO')
    obj = req.params.get('OBJ')
    fil = req.params.get('filter')
    recived_body = req.get_body()
    logging.info(geo + '\n' + obj + '\n' + fil)
    #logging.info(recived_body)
    try:
        jret = recived_body
        dataset = str(jret)
        dataset = dataset[3: len(dataset)-2]
        print(dataset,file=open("dataset.json","w+"))
    except urllib.error.HTTPError as e:        
        print('HTTPError: {}'.format(e.code))
    except urllib.error.URLError as e:
        print('URLError: {}'.format(e.reason))
    else:
        print('download good')

    #stringa = Parser()

    return json.dumps(dataset)
    #return func.HttpResponse(f"{recived_body}")
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