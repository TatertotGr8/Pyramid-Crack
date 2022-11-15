
# Pyramid-Crack

This is a program to find a quad desart pyramids in Minecraft verion 1.17.  

This program makes use of the [seed-finding libarys](https://kaptainwutax.seedfinding.com/) developed by [Kaptainwutax](https://github.com/KaptainWutax) and [Neil](https://github.com/hube12). To sreach within adjacent pyrmaid regions local to spawn and returns vaild world seeds that match the conditions of Rpos (0, 0), (0, 1), (1, 0) and (1, 1)

## Docmentation

To run this project, you will need to to have the a copy of [Java JDK 16](https://www.oracle.com/java/technologies/javase/jdk16-archive-downloads.html) installed and [Gradle](https://gradle.org/install/) installed. 
 You will also need a programing environment I recomend [IntelliJ IDEA](https://www.jetbrains.com/idea/) for this but any environment that could run Java and gradle would work such as VS code. 

Clone the project into program environment:

```bash
clone https://github.com/TatertotGr8/Pyramid-Crack.git
```

It should be fairly straight forward run the project and watch it go. 
Or watch the [showcase](https://youtu.be/YYb_mFQJszU) video found here. And for deeper understanding reffer to the writen documentation [here](https://docs.google.com/document/d/1S-tqtsDtqdalQDEEsopy5CnU4O1-bL9xtSGgOIrrxzI/edit#).


Issues relateing to complieing the porject are useally due to incompatiblely between your Gradle and Java installs. 
Check the gradle documentation for gradle releases Java incompatiblely. Allthough I used Java 16 and Gradle v6.7.1 anything *should* work as long as your Java is above version 11 else the libarys would be incompatible. 

To troubleshoot this on a windows system, go check the environment variables. Double check the varribles you would need a varrible **"JAVA_HOME"** is set to your Java JDK installed and the **"PATH"** varrible is set to the bin folder in your Java JDK folder. You would also need a varrible of your Gradle install **"Gradle_Home"**  and the bin folder added to the **"PATH"** varrible.

## Acknowledgements

 - [Kaptainwutax Minecraft Seed Finding libraries](https://kaptainwutax.seedfinding.com/)
  
 - [hube12 (Neil)](https://github.com/hube12)

 - [Minecraft@home](https://minecraftathome.com/)

## Support

For support or qustions about technical Minecraft, email tg24006@pathwayshigh.org or contact Tatertot#7962 on discord.

