# Working in this repo

This guide is for coding agents and for people. Sand Bank is a small banking app built in a
classic Model-View-Controller style. Read this before you change anything.

## Layout

```
app/src/main/kotlin/dev/sand/mvc/
  models/        AccountModel (the customer's account state), Transfer, baht()
  views/         one view per page: a view model plus a render function. No logic.
  controllers/   one controller per page: reads models, calls services, builds the view model
  services/      BankService (calls to the bank server) and FakeBankService
  Routes.kt      every page and action in the app
  Menu.kt        quick actions on the home page
  Main.kt        prints every page, confirms the draft transfer, prints home again
host-server/     Ktor server implementing api/openapi.yaml (separate deployable)
api/openapi.yaml HTTP form of BankService
```

## Where things go

| You want to… | Do this |
|---|---|
| Show something new on an existing page | Add a field to the page's view model (`views/`), fill it in the page's controller, render it in the view |
| Add a new page | Add a view and a controller, then register the page in `Routes.pages` |
| Add a quick action on the home page | Add it to `Menu.items` |
| Add something the user can do | Add a method on the controller and register it in `Routes.actions` |
| Store a new piece of account state | Add a property to `AccountModel` and reset it in `AccountModel.reset()` |
| Call the server | Add a method to `BankService`, implement it in `FakeBankService`, and add the endpoint to `api/openapi.yaml` and `host-server` |

## Conventions

- **Views only render.** They take a view model and return text. Decisions such as "show the
  loan offer?" are made in the controller and passed in as fields.
- **Controllers stay thin.** Read from `AccountModel`, call `BankService`, build the view model.
- **The server owns the balance.** After a transfer, store the balance that `BankService.transfer`
  returns. Never compute it on the client.
- **Money is whole baht** (`Long`). Format it with `baht(amount)`.
- **Tests reset shared state.** Call `AccountModel.reset()` in `@BeforeTest`.

## Example: how the fraud warning was added

1. `TransferViewModel` got a `showFraudWarning` field.
2. `TransferController.index()` calls `bank.fraudScore(...)` and sets the field when the score is 70 or more.
3. `TransferView` prints the warning line when the field is true.

## Commands

```
./gradlew build              # compile and run tests
./gradlew :app:run           # print the pages and run one transfer
./gradlew :host-server:run   # serve api/openapi.yaml on localhost:8080
```

Run `./gradlew build` before you finish. Add or update a test in `app/src/test` for new behaviour.

## Changing the server contract

`api/openapi.yaml`, `BankService` and `host-server` change together. Every server error is RFC 9457
Problem Details with a `supportedPath` field that says what to do instead.
