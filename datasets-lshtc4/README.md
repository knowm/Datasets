## Xeiam Datasets LSHTC4

[raw data](http://www.kaggle.com/c/lshtc/data) 

## How to Generate Java Data

1. Download raw data from here: http://www.kaggle.com/c/lshtc/data

1. put all files in `raw` folder in project root

1. Run `RawData2DB.java`. 

1. The database containing the data will appear in `db`.

1. Manually copy the `LSHTC4.*` files into the `src/main/resources` folder. There should be three files. 

## Build jar containing Data

1. using Maven, run `mvn clean install`

## Data Information

#### Train Sample Row

Data
314523, 165538, 76255, 335416, 416827 1:1 2:1 3:1 4:1 5:1 6:1 7:1 8:1 9:1 10:1 11:1 12:2 13:1 14:2 15:1 16:1 17:1 18:1 19:1 20:1 21:1 22:1 23:1 24:5 25:1 26:1 27:1 28:1 29:1 30:3 31:1 32:2 33:1 34:1 35:1 36:3 37:1 38:1 39:1 40:1 41:3 42:1 43:7 44:1 45:1 46:1 47:1 48:1 49:1 50:1 51:2 52:1 53:1 54:1 55:1 56:1 57:3 58:4 59:1 60:2 61:3 62:1 63:1 64:1 65:1 66:1 67:2 68:1 69:1 70:1 71:1 72:1 73:1 74:1 75:2 76:2 77:1 78:1 79:1 80:1 81:1

#### Test Sample Row

Id,Data
1,0 139:1 153:4 199:1 212:1 232:1 282:1 307:3 310:1 428:1 510:1 528:1 609:1 700:2 709:1 727:1 765:1 791:1 798:2 838:1 872:1 1007:1 1170:2 1374:1 1388:1 1409:1 1435:1 1892:1 2190:1 2197:1 2253:1 2348:2 2570:1 2628:1 2713:1 3066:1 3406:1 3619:2 3628:2 3636:1 3649:2 5068:1 8385:1 9371:1 11248:1 11806:1


#### Observations

longestLabelsStringLength: 1344
longestFeaturesStringLength: 47449
highestFeatureID: 1617899
highestFeatureValue: 1700
highestLabelID: 2817604

Train entries: 2,365,437
Test entries: 452,167
Total Entries: 2,817,604