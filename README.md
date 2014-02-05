## Xeiam Datasets

Xeiam Datasets is a Java library for conveniently working with machine learning datasets.  

## Description 

Each subproject wraps a single machine learning dataset, packaging the data into a jar that can easily be integrated into other Java projects. If you want 
to build the project yourself, note that the actual data is not hosted in the repo with the code, but must be downloaded separately first. Each module in this 
projects has its own README file with instructions on where to get the data and how to build the modules. If you just want to download the jars to use in 
your own project, the data IS included inside, and you don't have to separately download the data.

## License

[MIT](http://opensource.org/licenses/MIT)

## Included Datasets

* [Breast Cancer Wisconsin (Original)](http://archive.ics.uci.edu/ml/datasets/Breast+Cancer+Wisconsin+%28Original%29)
* [Census Income](http://archive.ics.uci.edu/ml/datasets/Census+Income)
* [MNIST](http://yann.lecun.com/exdb/mnist/)
* [Reuters-21578](http://archive.ics.uci.edu/ml/support/Reuters-21578+Text+Categorization+Collection)
* [CIFAR-10](http://www.cs.toronto.edu/~kriz/cifar.html)

## Example Usage

### Include Jar in Your Project

for example: `datasets-breast-cancer-wisconsin-orginal-1.2.0-SNAPSHOT.jar`

### Via Maven

Add to your pom file:

    <repositories>
        <repository>
            <id>sonatype-oss-public</id>
            <url>https://oss.sonatype.org/content/groups/public/</url>
            <releases>
                <enabled>true</enabled>
            </releases>
            <snapshots>
                <enabled>true</enabled>
            </snapshots>
        </repository>
    </repositories>

    <dependency>
        <groupId>com.xeiam.datasets</groupId>
        <artifactId>datasets-breast-cancer-wisconsin-orginal</artifactId>
        <version>1.2.0-SNAPSHOT</version>
    </dependency>
    
### Manual Download

[Sonatype snapshots repo](https://oss.sonatype.org/content/groups/public/com/xeiam/datasets/)

### Code Snippet

    List<BreastCancer> breastCancerTrainingData = BreastCancerDAO.selectTrainData();
    System.out.println("training data size: " + breastCancerTrainingData.size());

    List<BreastCancer> breastCancerTestData = BreastCancerDAO.selectTestData();
    System.out.println("test data size: " + breastCancerTestData.size());

### Result

    training data size: 500
    test data size: 182
    BreastCancer [sampleCodeNumber=1320077, clumpThickness=1, uniformityOfCellSize=1, uniformityOfCellShape=1, marginalAdhesion=1, singleEpithelialCellSize=1, bareNuclei=1, blandChromatin=1, normalNucleoli=1, mitoses=1, cellClass=2]
    
## Building

Xeiam Datasets is built with Maven.

    cd path/to/datasets-parent
    
#### Install to local repo

    mvn clean install
    
#### maven-license-plugin

    mvn license:check
    mvn license:format
    mvn license:remove
    
#### JavaDocs

    mvn javadoc:aggregate 

 
