# Resdecorator

Lightweight library for decorating Android app resources.
It allows you to overwrite how resources are accessed in Android without the additional hassle.

If you want to add internationalization to your app, with all the labels and messages defined in a backend system, CMS or similar, normally you would have to either:
* create alternative string resources in "values_locale" folder. But this is not flexible or dynamic, because you would have to:
generate a new strings.xml file for each language and import it into your app, and create a new release each time a new language is added or a translation is updated. OR
* extend the TextView classes (TextView, EditText, Button etc), and define a custom xml tag or similar, and implement a logic that maps a key to a translation retrieved from a web service OR 
* traverse the view tree and set the strings OR
* to actually call TextView#setText for all of the app's labels

Using this library you can just hook into the mechanism that resolves string ids so that each time you set it for the text (or hint) tag in the XML or by calling Context#getString your custom logic could overwrite the default string or decorate it.

## Disclaimer
The current version decorates the access of strings from resources.
Future versions will add the same approach for all resources accessible via the *Resources* class.

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
    compile 'com.github.bakehousedigital:resdecorator:0.5'
}
```

### Usage

Inject the ResourceContextWrapper by wrapping the Activity Context and providing your own implementation of the ResourceDecorator.
```
@Override
protected void attachBaseContext(Context newBase) {
    super.attachBaseContext(ResourceContextWrapper.wrap(newBase, new ResourceDecorator() {
        public String getString(Resources resources, int id, Object... formatArgs) {
            //Return whatever string. If the returned value is null, then the default
            //string resource implementation will be used -> Resources.getString(id);
            //...
        }
    }));
}
```

#### Important:
If you use **resdecorator** in conjunction with other libraries which overwrite the layout inflation mechanism, such as *Calligraphy*, don't forget to also add *ResourceContextWrapper.initialize(this);* in your *Activity.onCreate* before calling *super.onCreate*.
```
@Override
protected void onCreate(Bundle savedInstanceState) {
    ResourceContextWrapper.initialize(this);
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
}
```
If not, then the invocation of the *initialize* method is not necessary.

### Example

For example, to make every string retrieved from resources upper case and replace all spaces by underscores, in your activity do this:
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

* **rrdev** - *Initial work* - [bakehousedigital](https://github.com/bakehousedigital)


## License

```
Copyright 2018 bakehousedigital

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
