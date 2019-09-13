import urllib.request
import urllib.error
import json

class Dataset:
    def __init__(self, freq, geo, unit, objective, timePeriod):
        self.freq=freq
        self.geo=geo
        self.unit=unit
        self.objective=objective
        self.timePeriod=timePeriod


def Downloader(url="http://127.0.0.1:8080/data"):
    dataset = ""
    try:
        jret = urllib.request.urlopen(url).readlines()
        dataset = str(jret)
        dataset = dataset[3: len(dataset)-2]
        print(dataset,file=open("dataset.json","w+"))
    except urllib.error.HTTPError as e:        
        print('HTTPError: {}'.format(e.code))
    except urllib.error.URLError as e:
        print('URLError: {}'.format(e.reason))
    else:
        print('good')
    return dataset

def Parser():
    dataList =[]
    with open("dataset.json",'r') as file:
        parse = json.load(file)
    for row in parse:
        #print(row)
        newObj = Dataset(row[0],row[1],row[4],row[2],row[3])
        dataList.append(newObj)
    #print(dataList)
    return dataList


#Downloader()
row_list = Parser()
print(row_list)
