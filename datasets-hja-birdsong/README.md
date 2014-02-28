## Xeiam Datasets HJA Birdsong

[raw data](http://web.engr.oregonstate.edu/~briggsf/kdd2012datasets/hja_birdsong/)

## How to Generate Java Data

1. Download raw data from here: http://web.engr.oregonstate.edu/~briggsf/kdd2012datasets/hja_birdsong/

1. put all files (hierarchy.zip, test-remapped and train-remapped) in `raw` folder in project root

1. Run `DownloadWavFiles.java` and `RawData2DB.java`.

1. The database containing the data will appear in `db`.

1. Manually copy the `HJA_BIRDSONG.*` files into the `src/main/resources` folder. There should be three files. 

## Build jar containing Data

1. using Maven, run `mvn clean install`

## Data Information
