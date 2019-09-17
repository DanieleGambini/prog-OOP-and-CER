import azure.functions as func
#import logging
import json
from Stats import statsController

def main(req: func.HttpRequest) -> str:
    geo = req.params.get('GEO')
    obj = req.params.get('OBJ')
    fil = req.params.get('FILTER')
    recived_body = req.get_body()

    if geo is None:
        geo = ''
    if obj is None:
        obj = ''
    if fil is None:
        fil = '{"startYear":"2000","endYear":"2017"}'
    
    try:
        dataset = str(recived_body)
        dataset = dataset[2: len(dataset)-1]
        #print(dataset,file=open("dataset.json","w+"))
    except urllib.error.HTTPError as e:
        print('HTTPError: {}'.format(e.code))
    except urllib.error.URLError as e:
        print('URLError: {}'.format(e.reason))
    else:
        print('POST recived correctly')
    
    f = json.loads(fil)
    if int(f['startYear'])<2000 or int(f['endYear'])>2017:
        error = { 'error': 'Years insered are not valid'}
        return json.dumps(error)
    result = statsController(dataset, geo, obj, int(f['startYear']), int(f['endYear']))
    return json.dumps(result)