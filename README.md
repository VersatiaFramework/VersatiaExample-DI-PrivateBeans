    # VersatiaExample-DI-PrivateBeans

    Beans, their constructors and auto-invoked functions may be `private`: the generated code falls back to reflection for them and nothing has to be widened.

    Part of the [Versatia examples](https://github.com/VersatiaFramework/VersatiaExamples); documentation: [Direct access versus reflection](https://versatiaframework.github.io/VersatiaDocs/docs/internals/descriptor#direct-access-versus-reflection).

    **Start with** [`Vault.kt`](src/main/kotlin/com/github/marcoral/versatia/example/privatebeans/Vault.kt).

    ## What the log shows

    ```
    [Key] created, a private class
[Vault] created with a private Key
[Auditor] created
[Auditor] audit() called through reflection with the Key
    ```


## Things to try

- Open the generated descriptor: the private classes have `Instantiator.Reflective(...)` with JVM
  binary names, `audit` has `Invoker.Reflective(...)`. Make `Key` public and rebuild: its element
  switches to `Instantiator.Direct { _ -> Key() }`.

## Building

Requires JDK 21 and VersatiaAPI in your local Maven repository (`./gradlew publishToMavenLocal`
in its checkout); running it requires a Paper 1.21.4 server with VersatiaCore installed.

```bash
./gradlew build
```

To have the JAR copied to your server after every build, copy `local.properties.example` to
`local.properties` and set `pluginsDir`. The generated descriptor is in
`build/generated/ksp/main/kotlin`; open it to see exactly what the runtime executes.
