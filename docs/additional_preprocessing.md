# Additional preprocessing

`PSIMiner` can perform additional preprocessing increasing the chances that projects in the dataset are opened correctly by IDEA.
However, additional preprocessing **mutates** the original dataset: **adds**, **removes** or **updates** files.

To enable additional preprocessing add a `additional preprocessing` field in the config and set `enable: true`:

```json
{
  "additional preprocessing": {
    "enable": true
  }
}
```

## Types of preprocessing

### Deleting `.idea` folders.

It is always turned on when `additional preprocessing` is enabled.

### Adding `local.properties` files with path to Android SDK. Without it Android projects are opened incorrectly.

To make sure that more Android projects are opened correctly you should set the `androidSdk`
field in the config to the Android SDK path (`$ANDROID_HOME`):

```json
{
  "additional preprocessing": {
    "enable": true,
    "androidSdkHome": "/absolute/path/to/android/home"
  }
}
```
