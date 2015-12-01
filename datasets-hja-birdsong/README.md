## Knowm Datasets HJA Birdsong

[raw data](http://web.engr.oregonstate.edu/~briggsf/kdd2012datasets/hja_birdsong/)

## How to Generate Java Data

1. Download raw data from here: <http://web.engr.oregonstate.edu/~briggsf/kdd2012datasets/hja_birdsong/>
1. put all files (hierarchy.zip, test-remapped and train-remapped) in `raw` folder in project root
1. Run `DownloadWavFiles.java` and `RawData2DB.java`.
1. The database containing the data will appear in `db`.
1. Manually copy the `DB_HJA_BIRDSONG.*` files into some folder, which you will point to later in an app using the data such as `/usr/local/Datasets/`. There should be four files. 

## Alternatively. Download Data from Google Drive

1. Download all DB files from here: <https://drive.google.com/folderview?id=0ByP7_A9vXm17VXhuZzBrcnNubEE&usp=sharing#list>
1. Manually copy the `DB_HJA_BIRDSONG.*` files into some folder, which you will point to later in an app using the data such as `/usr/local/Datasets/`. There should be four files. 

## Birdsong Spectrograph Viewer

See: `com.xeiam.datasets.samples.HJABirdsongSpectrogramViewer`. Make sure you've placed the generated/downloaded DB data at the location specified at the line: `HJABirdsongDAO.init("/usr/local/Datasets/"); // setup data`.
