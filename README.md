## Xeiam Datasets

Xeiam Datasets is a Java library for conveniently working with machine learning datasets.  

## Description 

The philosophy of this open source project is simple - take several diverse datasets, which all have their own custom formats, and convert them all into a unified 
format with a unified API for accessing the data. Each module has a `RawData2DB` class, which parses the raw data and puts each data object into a file-based HSQLDB database. 
No separate database installation is necessary. The generated database files have been uploaded to Xeiam's Google Drive account [here](https://drive.google.com/folderview?id=0ByP7_A9vXm17VXhuZzBrcnNubEE&usp=sharing#list).
The data is accessed for client apps through a DAO class, with methods so easy, even a child could understand:

Sample code:

    LSHTC4DAO.init("/Users/timmolter/Documents/Datasets"); // setup data

    // print number of objects
    long count = LSHTC4DAO.selectCount();
    System.out.println("count= " + count);
    
    // loop through first 10 LSHTC4 objects
    for (int i = 1; i <= 10; i++) {

      LSHTC4 lSHTC4 = LSHTC4DAO.selectSingle(i);
      System.out.println(lSHTC4.toString());
    }
    
    LSHTC4DAO.release(); // release data resources
    
    
Output:

    count= 2817603
    
    LSHTC4 [id=1, labels=, features=139:1,153:4,199:1,212:1,232:1,282:1,307:3,310:1,428:1,510:1,528:1,609:1,700:2,709:1,727:1,765:1,791:1,798:2,838:1,872:1,1007:1,1170:2,1374:1,1388:1,1409:1,1435:1,1892:1,2190:1,2197:1,2253:1,2348:2,2570:1,2628:1,2713:1,3066:1,3406:1,3619:2,3628:2,3636:1,3649:2,5068:1,8385:1,9371:1,11248:1,11806:1,]
    LSHTC4 [id=2, labels=, features=41:3,131:2,218:1,254:1,289:1,501:4,511:1,519:3,526:1,527:1,539:1,542:1,543:2,551:2,558:3,605:2,977:2,2748:1,2867:1,3849:1,4032:1,5030:1,19156:1,]
    LSHTC4 [id=3, labels=, features=41:1,519:2,532:1,574:1,576:1,1032:1,1413:1,4285:1,8865:1,11071:1,24481:1,83715:1,]
    LSHTC4 [id=4, labels=, features=8:1,26:1,29:1,44:1,48:1,107:1,118:1,137:1,145:1,196:1,197:1,211:1,354:1,400:1,403:1,409:1,415:1,432:1,439:1,442:1,459:1,536:2,551:1,558:1,605:1,612:1,661:1,689:1,695:1,805:3,816:1,834:1,854:5,867:1,883:1,889:1,891:1,902:1,944:2,980:1,1139:1,1273:1,1287:1,1345:1,1415:1,1614:2,1664:1,1713:1,1776:2,1817:1,1861:1,1956:1,2100:1,2105:1,2121:1,2558:2,2564:1,2619:1,3018:1,3045:1,3055:1,3061:2,3217:2,3233:1,3301:1,3755:1,5504:1,6555:1,6942:1,7102:1,7901:1,10298:1,11317:1,12780:1,14305:1,16756:1,27769:1,28416:1,29278:3,32759:1,181529:1,1003324:1,]
    LSHTC4 [id=5, labels=, features=11:1,26:1,40:1,49:1,139:1,146:1,153:3,175:1,197:1,198:1,199:2,215:2,226:1,228:1,237:2,238:1,239:2,240:1,242:1,253:1,262:1,274:1,286:1,297:1,307:1,316:2,317:1,318:4,326:1,354:1,364:1,375:1,430:1,439:2,463:1,474:1,490:1,491:1,583:3,596:1,597:1,605:1,614:1,615:2,647:1,730:2,752:1,765:1,769:1,777:3,791:1,793:1,798:6,867:2,874:1,891:1,1006:1,1018:1,1092:1,1099:2,1106:2,1116:1,1138:1,1155:1,1159:3,1167:1,1169:1,1171:1,1180:1,1184:2,1317:1,1330:1,1394:1,1398:1,1414:3,1449:1,1467:1,1469:1,1515:1,1547:1,1575:1,1771:1,1797:1,1842:2,1918:1,1932:1,2009:1,2066:1,2103:1,2115:1,2135:1,2143:1,2180:1,2184:1,2192:1,2196:1,2197:1,2220:2,2275:1,2306:1,2334:1,2342:1,2344:1,2419:1,2557:2,2610:1,2652:1,2934:1,2969:1,3023:1,3026:1,3032:1,3048:3,3053:2,3380:2,3403:2,3507:1,3664:1,3849:1,3964:16,3970:1,3984:1,4016:1,4017:4,4205:1,4302:1,4336:1,4353:1,4524:1,4548:1,4571:1,4665:1,4667:1,4672:1,5083:2,5134:1,5930:1,6229:1,6738:1,6977:1,7404:1,8540:1,9532:2,11399:1,12822:1,15406:1,16929:1,17726:1,19875:1,20093:1,20597:1,20641:1,20655:1,26618:1,27756:1,36028:1,63893:1,70093:1,121950:1,171358:1,191665:1,866061:1,]
    LSHTC4 [id=6, labels=, features=18:1,19:1,64:1,89:1,123:1,147:1,198:1,264:1,356:1,387:1,491:2,511:2,521:1,527:1,529:2,561:4,632:1,712:1,761:1,903:1,991:1,1002:1,1105:1,1299:1,1565:1,1620:1,1651:1,1697:1,1832:1,3591:1,4607:1,4718:1,6248:1,7963:1,23274:2,]
    LSHTC4 [id=7, labels=, features=11:2,26:2,36:1,62:2,67:1,70:1,81:1,99:1,155:1,185:1,197:3,204:3,211:5,229:1,230:1,231:1,246:1,344:2,347:1,375:1,397:1,401:2,413:1,415:1,458:2,491:1,497:1,539:1,558:1,587:1,692:2,745:1,752:1,761:1,812:2,815:1,827:1,829:1,854:12,944:1,978:2,991:1,1001:2,1109:1,1159:1,1193:1,1247:1,1300:1,1380:1,1414:3,1518:1,1544:1,1634:1,1661:16,1670:1,1788:2,1813:2,1834:1,1846:1,1879:1,2062:1,2128:1,2220:1,2236:2,2562:2,2578:2,2586:7,2683:1,2962:1,3014:1,3019:1,3734:2,3826:1,3999:1,4052:1,4267:1,4471:1,4752:1,4756:1,4811:1,4850:2,4963:1,5071:1,5317:2,5459:1,5497:1,5509:3,5698:2,6899:1,7045:1,7217:1,7641:1,7924:1,7985:1,8010:1,8176:1,8482:1,8942:1,10605:1,10682:1,10706:1,12306:1,12307:1,12425:2,12555:1,12681:1,12961:1,13995:1,13998:1,14000:1,14214:1,14826:1,15493:1,16852:1,21690:3,26455:1,26503:1,34393:1,35307:1,42172:1,43814:1,47525:1,50601:1,65466:1,74704:1,93306:1,93846:1,98361:1,143927:1,512967:1,581083:1,892311:1,922750:1,]
    LSHTC4 [id=8, labels=, features=20:1,30:1,32:1,44:1,81:1,104:1,114:1,122:1,133:1,135:2,140:1,178:1,202:1,211:1,215:1,219:2,228:2,229:1,312:2,367:1,475:1,587:1,740:1,750:1,769:1,777:1,778:3,829:1,830:1,834:1,856:1,1024:1,1083:5,1099:1,1100:2,1102:5,1106:12,1118:1,1129:1,1156:1,1176:1,1377:1,1681:1,1786:1,1804:2,2088:1,2126:1,2295:1,3018:2,3044:2,3127:1,4175:1,4440:1,5115:1,5568:1,5774:1,5913:2,5923:1,7958:1,8112:1,9324:3,10808:1,12594:2,12692:1,12715:1,16618:1,18828:1,18829:1,19913:1,19920:4,20093:5,20193:1,21208:1,21213:1,25433:1,36336:1,55404:1,69755:1,113192:1,]
    LSHTC4 [id=9, labels=, features=24:1,41:1,81:1,122:2,131:2,196:1,197:1,199:2,219:1,230:3,310:1,318:2,328:1,346:2,354:2,375:1,378:1,395:1,400:1,415:1,430:1,464:1,501:1,559:3,561:3,567:2,570:4,576:1,589:1,601:1,605:1,633:1,692:3,717:1,721:3,765:1,773:1,791:3,818:1,841:1,903:1,916:1,977:1,1000:1,1019:1,1046:1,1078:1,1106:1,1109:1,1163:1,1249:2,1266:1,1413:1,1556:1,1563:1,1664:1,1716:1,1742:2,1756:1,1782:1,1793:1,1915:1,1966:1,2032:1,2369:1,2687:2,2695:1,2957:1,3365:1,3519:1,3581:1,3698:1,4548:1,4570:1,5126:3,5526:3,5954:2,6014:1,7104:1,7124:1,7652:1,8532:1,10305:1,10637:1,10774:1,11256:2,11892:1,12116:1,14386:1,14732:1,17880:5,19492:4,23460:1,23618:1,30520:2,33822:1,42461:1,57833:1,386140:1,691708:1,1558913:1,]
    LSHTC4 [id=10, labels=, features=40:1,41:1,44:1,48:2,49:1,68:1,95:1,111:1,153:4,162:1,196:1,219:1,228:1,229:1,232:1,238:1,239:2,242:2,247:2,276:1,297:2,306:1,307:1,316:1,317:1,375:1,430:1,510:1,516:1,582:1,612:1,717:1,728:2,761:1,764:1,776:1,783:1,797:1,815:1,915:1,1116:1,1337:1,1441:1,1680:1,2116:2,2118:1,2119:1,2192:1,2194:1,2322:1,2347:1,2354:1,2613:1,2636:1,2748:1,2930:1,3048:1,3057:1,3140:1,3229:1,3893:1,4030:1,4252:1,4984:1,5068:1,6599:1,7108:1,8540:1,10639:1,10666:1,10670:2,10676:1,14070:5,14321:1,14364:2,24700:1,26766:1,27895:1,63406:1,166985:1,601892:1,]

The first time the DAO class is used, it attempts to download the database files from Google Drive. If there are problems, like when the file is too big, a messasge is printed 
directing you to download the files manually.

If you prefer to build the project yourself, note that the actual data is not hosted in the repo with the code, but must be downloaded separately first. Each module in this 
projects has its own README file with instructions on where to get the data and how to build the modules. 

## License

[MIT](http://opensource.org/licenses/MIT)

## Included Datasets

* [Breast Cancer Wisconsin (Original)](http://archive.ics.uci.edu/ml/datasets/Breast+Cancer+Wisconsin+%28Original%29)
* [Census Income](http://archive.ics.uci.edu/ml/datasets/Census+Income)
* [MNIST](http://yann.lecun.com/exdb/mnist/)
* [Reuters-21578](http://archive.ics.uci.edu/ml/support/Reuters-21578+Text+Categorization+Collection)
* [CIFAR-10](http://www.cs.toronto.edu/~kriz/cifar.html)
* [LSHTC4](http://www.kaggle.com/c/lshtc/data)

## Example Usage

### Include Jar in Your Project

for example: `datasets-breast-cancer-wisconsin-orginal-2.0.0-SNAPSHOT.jar`

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
        <version>2.0.0-SNAPSHOT</version>
    </dependency>
    
### Manual Download

[Sonatype snapshots repo](https://oss.sonatype.org/content/groups/public/com/xeiam/datasets/)

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

