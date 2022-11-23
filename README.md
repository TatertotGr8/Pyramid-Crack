# Pyramid-Crack

This is a program to find a quad desert pyramid in Minecraft version 1.17.  

This program makes use of the [seed-finding libarys](https://kaptainwutax.seedfinding.com/) developed by [Kaptainwutax](https://github.com/KaptainWutax) and [Neil](https://github.com/hube12). To search within adjacent pyramid regions local to spawn and returns valid world seeds that match the conditions of the region positions, (0, 0), (0, 1), (1, 0), and (1, 1).

## Use Documentation

To run this project, you will need to have a copy of [Java JDK 16](https://www.oracle.com/java/technologies/javase/jdk16-archive-downloads.html) installed and [Gradle](https://gradle.org/install/) installed. 
 You will also need a programming environment I recommend [IntelliJ IDEA](https://www.jetbrains.com/idea/) for this but any environment that could run Java and Gradle would work such as VS Code or Eclipse. 

Clone the project into the program environment:

```bash
https://github.com/TatertotGr8/Pyramid-Crack.git
```

It should be fairly straightforward forward run the project and watch it go. Or watch the [showcase](https://youtu.be/YYb_mFQJszU) video found here. And for deeper understanding refer to the written documentation [here](https://docs.google.com/document/d/1S-tqtsDtqdalQDEEsopy5CnU4O1-bL9xtSGgOIrrxzI/edit#).


Issues related to compiling the project are usually due to incompatibility between your Gradle and Java downloads. 
Check the Gradle documentation for Gradle releases Java incompatible. Although I used Java 16 and Gradle v6.7.1 anything *should* work as long as your Java is above version 11 because the libraries would be incompatible with earlier releases of Java. 

To troubleshoot this on a windows system, go check the environment variables. Double check the variables you would need a variable titled **"JAVA_HOME"** is set to your Java JDK installed and the **"PATH"** variable is set to the bin folder in your Java JDK folder. You would also need a variable of your Gradle install **"Gradle_Home"**  and the bin folder added to the **"PATH"** variable.

## Acknowledgements

 - [Kaptainwutax Minecraft Seed Finding libraries](https://kaptainwutax.seedfinding.com/)
  
 - [hube12 (Neil)](https://github.com/hube12)

 - [Minecraft@home](https://minecraftathome.com/)

## Support

For support or questions about technical Minecraft, email tg24006@pathwayshigh.org or contact Tatertot#7962 on discord.

