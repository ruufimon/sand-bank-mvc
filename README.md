# Sand Bank (MVC)

Sand Bank built in a traditional Model-View-Controller style: code is organised by layer
(models, views, controllers, services), with a shared `Routes` list and a shared `AccountModel`.

It's the comparison baseline for
[agent-friendly-architecture](https://github.com/ruufimon/agent-friendly-architecture): the
same product, the same server and the same console output, organised the conventional way.
The test harness lives in that repo under `eval/`.

See [AGENTS.md](AGENTS.md) for where code goes.

## Run

Requires JDK 21.

```
./gradlew build
./gradlew :app:run
./gradlew :host-server:run     # serves api/openapi.yaml on http://localhost:8080/host/v1
```
