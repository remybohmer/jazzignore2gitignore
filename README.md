# jazzignore2gitignore
Converts all '.jazzignore' files in a RTC working tree to '.gitignore' files,
The original .jazzignore files remain in the workarea, and in each directory a corresponding .gitignore
will be created.

# Build Instruction

Run from the root of this project:
$ mvn clean package

# Usage instruction

You should do the following (to convert a RTC sandbox to git repo):
1. Go to the root directory of the RTC workarea
2. Run the following command
   $ java -jar <this-project>/target/jazzignore2gitignore-0.1-jar-with-dependencies.jar /path/to/rtc/workarea

Once finished all generated .gitignore files can be committed inside a Git-repository.

Note: It overwrites all existing '.gitignore' files if there is a '.jazzignore' file in the same
      directory plus the root '.gitignore'

