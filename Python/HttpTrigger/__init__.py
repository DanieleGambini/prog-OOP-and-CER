import azure.functions as func
#import logging
import urllib
import json

#from Stats import statsController
from cache import cacheController

def main(req: func.HttpRequest) -> str:
    fil = req.params.get('FILTER')
    recived_body = req.get_body()
    
    try:
        dataset = str(recived_body)
        dataset = dataset[2: len(dataset)-1]
    except urllib.error.HTTPError as e:
        print('HTTPError: {}'.format(e.code))
    except urllib.error.URLError as e:
        print('URLError: {}'.format(e.reason))

#    f = json.loads(fil)
    result = cacheController(dataset, fil)
    return json.dumps(result)