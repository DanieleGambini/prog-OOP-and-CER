import logging
import json
from Stats import Stats

import azure.functions as func

# "route": "stats/{geo:length(2):alpha}.{obj:length(3,5)}/{filter}",

def main(req: func.HttpRequest) -> str:
    logging.info('Submit the dataset as a POST')
    
    geo = req.params.get('GEO')
    obj = req.params.get('OBJ')
    fil = req.params.get('FILTER')
    recived_body = req.get_body()
    #logging.info(geo + '\n' + obj + '\n' + fil)
    #logging.info(recived_body)
    try:
        jret = recived_body
        dataset = str(jret)
        dataset = dataset[2: len(dataset)-1]
        print(dataset,file=open("dataset.json","w+"))
    except urllib.error.HTTPError as e:        
        print('HTTPError: {}'.format(e.code))
    except urllib.error.URLError as e:
        print('URLError: {}'.format(e.reason))
    else:
        print('download good')

    f = json.loads(fil)
    result = Stats(geo, obj, int(f['startYear']), int(f['endYear']))

    return json.dumps(result)

