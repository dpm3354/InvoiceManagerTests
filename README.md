# Invoice Manager Tests

## IDE Settings

This project uses lombok to remove a lot of Java boilerplate. 

Check out [here](https://projectlombok.org/) to learn about Lombok. 
Check out [this stackoverflow post](https://stackoverflow.com/questions/24006937/lombok-annotations-do-not-compile-under-intellij-idea) on configuring lombok with Intellij.

## Running Tests

    ./gradlew clean test
    
## Configuration

Configuration are defined via application properties. The defaults should suffice in most cases.

You may override them by passing them in. For example:

    ./gradlew clean test -DbaseUrl=http://127.0.0.1:8089
