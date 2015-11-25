## Xeiam Datasets UCSD

[raw data](http://www.svcl.ucsd.edu/projects/anomaly/dataset.html) 

## How to Generate Java Data

1. Download raw data from here: http://www.svcl.ucsd.edu/projects/anomaly/dataset.html

1. put all files in `raw` folder in project root

1. Convert tifs to pngs because Java won't work with these tifs for some reason

    find . -iname "*.tif" -type f -exec sh -c 'sips -s format png "$0" --out "${0%.tif}.png"' {} \;

1. Run `RawData2DB.java` with program argument `DB_HSQLDB_FILE.properties`. 

1. The database containing the data will appear in `db`.

1. Manually copy the `DB_UCSD_ANOMALY.*` files into the `src/main/resources` folder. There should be three files. 

## How to build jar containing Data

1. Using Maven, run `mvn clean install`.