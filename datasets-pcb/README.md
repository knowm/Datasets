## Knowm Datasets PCB

[raw data](https://www.caa.tuwien.ac.at/cvl/research/cvl-databases/pcb-dslr-dataset/) 

## How to Generate Java Data

1. Download raw data from here: https://www.caa.tuwien.ac.at/cvl/research/cvl-databases/pcb-dslr-dataset/

1. put all folder after unzipping in `raw` folder in project root

1. Run `RawData2DB.java`. 

1. The database containing the data will appear in `db`.

1. Manually copy the `DB_PCB.*` files into the `/usr/local/Datasets` folder. There should be four files. 

## Build jar containing Data

1. Using Maven, run `mvn clean install`.