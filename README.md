# java-email-send-form

This sample will show you to easily grab information for a web form and send it in an email with the Nylas Java SDK.

## Setup

### System dependencies

- Java 18.0.2
- Maven 3.8.6

### Gather environment variables

You'll need the following values:

```text
ACCESS_TOKEN = ""
```

Add the above values to a new `.env` file:

```bash
$ touch .env # Then add your env variables
```

### Install dependencies

```bash
org.slf4j / slf4j-simple / 1.7.25
com.sparkjava / spark-core / 2.9.4
com.nylas.sdk / nylas-java-sdk / 1.15.0
io.github.cdimascio / dotenv-java / 2.2.4
```

# Compilation

To compile the comment we need to use this `maven` command:

```bash
mvn clean compile
```

## Usage

Run the web server using the `maven` command:

```bash
$ mvn exec:java -Dexec.mainClass="Main"
```

On your web broser, type the following:

```bash
localhost:4567/feedback
```

When your message is successfully sent, you will receive a message on the web page and will be redirected to the form


## Learn more

Visit our [Nylas Java SDK documentation](https://developer.nylas.com/docs/developer-tools/sdk/java-sdk/) to learn more.
