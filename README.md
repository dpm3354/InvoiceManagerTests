# Invoice Manager Tests

## Running Tests

    ./gradlew clean test
    
## Configuration

Configuration are defined via application properties. The defaults should suffice in most cases.

You may override them by passing them in. For example:

    ./gradlew clean test -DbaseUrl=http://127.0.0.1:8089
