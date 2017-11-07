# Resdecorator

Light weight library for decorating app Resources.
The idea behind it was to be able to use a component that can overwrite the way the resources are accessed in Android without the additional hastle.

An example could be to add localization to your app at some point.
Normally you would have to either:
* extend the TextView classes (TextView, EditText, Button etc), and define a custom xml tag or similar
* or traverse the view tree
* or to actually call TextView#setText for all of the app's labels

Using this library you can just hook into string id resolving so that each time you set it for the text or hint tag in the XML or by actually using Context#getString your custom logic could overwrite the default string or decorate it.

## Getting Started

Add it in your root build.gradle at the end of repositories:
```
allprojects {
    repositories {
        maven { url 'https://jitpack.io' }
    }
}
```
Add the dependency
```
dependencies {
    compile 'com.github.bakehousedigital:resdecorator:0.4'
}
```

### Usage and Example

Inject the ResourceContextWrapper by wrapping the Activity Context. 
For example, let's say that for some reason you want every String resource retrieved from resources to be upperCase and have all the spaces replaced by underscores:

```
@Override
protected void attachBaseContext(Context newBase) {
    super.attachBaseContext(ResourceContextWrapper.wrap(newBase, new ResourceDecorator() {
        public String getString(Resources resources, int id, Object... formatArgs) {
            return resources.getString(id).toUpperCase().replacesAll(" ", "_");
        }
    }));
}
```

That is all!

## Authors

* **Roman Reaboi** - *Initial work* - [bakehousedigital](https://github.com/bakehousedigital)


## License

```
Copyright 2017 bakehousedigital

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
```
## Acknowledgments

* A big thanks to Calligraphy devs (https://github.com/chrisjenx/Calligraphy), your lib is awesome!
