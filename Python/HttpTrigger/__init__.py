import azure.functions as func
#import logging
import json
from Stats import statsController

def main(req: func.HttpRequest) -> str:
    fil = req.params.get('FILTER')
    recived_body = req.get_body()
    
    try:
        dataset = str(recived_body)
        dataset = dataset[2: len(dataset)-1]
        print(dataset,file=open("dataset.json","w+"))
    except urllib.error.HTTPError as e:
        print('HTTPError: {}'.format(e.code))
    except urllib.error.URLError as e:
        print('URLError: {}'.format(e.reason))
    else:
        print('POST recived correctly')

    f = json.loads(fil)
    
    result = statsController(dataset, f)
    return json.dumps(result)