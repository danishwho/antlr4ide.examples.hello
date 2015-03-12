#Antlr4ide Example
This is an example project using the [Antlr4ide eclipse plugin](https://github.com/jknack/antlr4ide).

##Pre-requisites
Install Antlr4ide following the [instructions](https://github.com/jknack/antlr4ide).

Note: if you're using java 8 you may get an error when running antlr.  As of writing (Feb 2015) antlr requires java7 and fails to run correctly.  In this case, install java7 first and add the following to your eclipse.ini:

    -vm
    /path/to/java7/bin/javaw.exe

Note this has to come before the `-vmargs` entry.

##Running
1. Right-click in [`ParserMain.java`](src/antlr4ide/examples/hello/ParserMain.java) and select `Run As->Java Application`.
2. In the console window type `hello world` and then either Control-D (mac/nix) or Ctrl-Z (Windows)
3. Antlr should respond with `(r hello world)`

#Creating the project
The project should build and run as is.  However, in case it's useful, here are the steps I followed to create it.

1. Create a new Java Project: `File->New->Java Project`.  Other than supplying the project name, everything else was left as default.
2. Created a new folder named `grammar` to hold grammar definition: right-click Project in Package Explorer then select `New->Folder`. 
2. Changed the directory into which Antlr generates source files.  This is purely personal: I use `src-gen` as a convention for generated files in other projects so set it up here too.  So the generated files are in [src-gen/antlr4](src-gen/antlr4).  You can leave this as the default (`target/generated-sources/antlr4`); it's set in `Preferences->Antlr 4->Tool->Directory`. (The Preferences menu is under 'Eclipse' on Mac, 'Window' on nix/Windows).  I'll refer to this as the *generation folder* below.
3. Created the grammar file: right-click the `grammar` folder then `New->File`.  Note the filename extension must be `g4`.
4. Pasted in the sample grammar contents from the [Antlr website](https://theantlrguy.atlassian.net/wiki/display/ANTLR4/Getting+Started+with+ANTLR+v4).
5. Following some [forum advice](https://groups.google.com/forum/?hl=en#!topic/antlr4ide/pOYk3XOaPUg), [added a header](grammar/Hello.g4) to specify the java package used by antlr when generating the parser, lexer, etc.  Antlr uses the default java package unless you tell it otherwise which (1) can make linking difficult and (2) isn't really good practice anyway.
6. Saved the grammar file.  The console window shows the build progress and should(!) indicate a successful build.  Assuming so, the generated sources will be in the *generation folder*.  
7. At this point Eclipse doesn't know to look for java files to compile in the generation directory; so we have to tell it:  
    1. Right-click on the project in Package Explorer
    2. Select `Java Build Path` then click the `Source` tab
    3. Click `Add Folder`
    4. Select the *generation folder*: in my case, `src-gen/antlr4`.  Click OK.
8. At this point Eclipse will try to compile the generated files.  However, the compilation will fail because Eclipse can't find the Antlr runtime libraries.  You can fix this by [downloading the antlr runtime jar](http://www.antlr.org/) and referencing it from the project; however you need to get the same version that's bundled in the Antlr4ide plugin.  I chose to use the bundled version instead; however it's wrapped up inside the plugin jar.  Here's the really-hacky way I did it:
    1. Right-click the project in Package Explorer and select `New->Folder`.
    2. Name the folder `libraries`.
    3. Opened the antlr4ide plugin jar with an unzip tool.  The plugin jar is `<eclipse-install-dir>/plugins/antlr4ide.core_0.3.5.jar` on my installation
    3. Located `antlr-4.4-complete.jar` in the lib folder of the jar & copied to the `libraries` folder I just created in the project
    4. Refresh the project so the library shows up in Package Explorer: highlight the project and hit F5.
    5. Open Project Properties->Java Build Path.  Click the `Libraries` tab, select `Add JARs` and select the downloaded Antlr jar in the list. Close the Properties dialogue.
    6. Select `Clean` from the `Project` menu & clean the project.  All the compilation errors should disappear.
9. Added a ['Main'](src/antlr4ide/examples/hello/ParserMain.java) class to run the parser.  Right-click on the `src` folder and add a new class named `ParserMain`.  Tick the box to generate a 'main' method.  Check the package matches that specified in the grammar file header section (makes linking easier) - for me it's `antlr4ide.examples.hello`.
10. The contents of the `main()` method are taken from [the Antlr book](https://pragprog.com/book/tpantlr2/the-definitive-antlr-4-reference) p33.

That's it.  You can test it by running as per earlier instructions.
