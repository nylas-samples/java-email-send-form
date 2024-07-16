# java-email-send-form

This sample will show you to easily grab information for a web form and send it in an email with the Nylas Java SDK.

## Setup

### System dependencies

- Java 18.0.2
- Maven 3.8.6

### Gather environment variables

You'll need the following values:

```text
NYLAS_API_KEY=
NYLAS_API_URI=
NYLAS_GRANT_ID=
```

Add the above values to a new `.env` file:

```bash
$ touch .env # Then add your env variables
```

### Install dependenciese

```bash
mvn clean install
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

On your web broser goto `localhost:4567/feedback` to complete the email form.

When your message is successfully sent, you will receive a message on the web page and will be redirected to the form


## Learn more

Visit our [Nylas Java SDK documentation](https://developer.nylas.com/docs/developer-tools/sdk/java-sdk/) to learn more.
