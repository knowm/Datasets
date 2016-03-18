## Knowm Datasets Breast Cancer Wisconsin Original

[raw data](http://archive.ics.uci.edu/ml/datasets/Breast+Cancer+Wisconsin+%28Original%29)

## How to Get the Data

1. Manually download the database files that have been uploaded to Knowm's Google Drive account [here](https://drive.google.com/folderview?id=0ByP7_A9vXm17VXhuZzBrcnNubEE&usp=sharing#list).
1. Manually copy the `DB_PCB.*` files into the `/usr/local/Datasets` folder. There should be four files. 

## How to Generate Data

1. Download raw data from here: http://archive.ics.uci.edu/ml/datasets/Breast+Cancer+Wisconsin+%28Original%29
1. put the `breast-cancer-wisconsin.data` file in a `raw` folder in project root
1. Run `RawData2DB.java`. 
1. The database containing the data will appear in `db`.
1. Manually copy the `DB_BREAST_CANCER.*` files into the `/usr/local/Datasets` folder. There should be 3 files. 

## How to Use the Data in You App

1. Put the desired jar on the classpath of your application. This will give you access to the DAO and Bean classes.
1. Just call `init` on the DAO class you are using, passing it the path to the database files that you downloaded or generated above. When finished with the data, call `release`. The following example is for the BreastCancer dataset. For more examples see the `datasets-samples` module. 


    try {
      BreastCancerDAO.init("/usr/local/Datasets/"); // setup data
      BreastCancerDemo demo = new BreastCancerDemo();
      demo.go();
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      BreastCancerDAO.release(); // release data resources
    }
