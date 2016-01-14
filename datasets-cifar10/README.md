## Knowm Datasets CIFAR-10

[raw data](http://www.cs.toronto.edu/~kriz/cifar.html) 

## How to Generate Java Data

1. Download raw data from here: http://www.cs.toronto.edu/~kriz/cifar.html

1. put all files in `raw` folder in project root

1. Run `RawData2DB.java`. 

1. The database containing the data will appear in `db`.

1. Manually copy the `DB_CIFAR.*` files into the `src/main/resources` folder. There should be three files. 

## Build jar containing Data

1. Using Maven, run `mvn clean install`.