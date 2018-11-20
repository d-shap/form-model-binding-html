# Form model html binding
Form model html binding is a form model binding implementation for HTML.

Form model library mediates between the source HTML and the application and encapsulates the complexity of the source HTML.
The essential parts of the HTML are described in the form definition XML.
Then this form definition is binded with the source HTML and the result of this binding is a binded form.
Binded elements can be obtained by the application from this binded form.

For example, suppose the following source HTML:
```
<html>
    <head>
        <title>Test page</title>
    </head>
    <body>
        <p>Some text.</p>
        <p>Some other text.</p>
        <p>Some more text.</p>
    </body>
</html>
```

To extract the *&lt;p&gt;* tags text, suppose the following form definition:
```
<?xml version='1.0'?>
<ns1:form id='p-extractor' xmlns:ns1='http://d-shap.ru/schema/form-model/1.0'>
    <ns1:element id='p-element' lookup='p' type='required+'/>
</ns1:form>
```

Here we define that the form contains one or more *&lt;p&gt;* tags.

The following code implements the binding:
```
// Load form definitions
FormDefinitions formDefinitions = new FormDefinitions();
File file = new File("file with the form definition");
FormDefinitionsLoader formDefinitionsLoader = new FormXmlDefinitionsFileLoader(file);
formDefinitionsLoader.load(formDefinitions);
HtmlFormBinder formBinder = new HtmlFormBinder(formDefinitions);

// Bind the HTML
Document document = formBinder.bindHtml(html, "p-extractor");

// Get the binded elements and text
List<Element> elements = formBinder.getElementsWithId(document, "p-element");
List<HtmlBindedElement> bindedElements = formBinder.getBindedElements(elements);
for (HtmlBindedElement bindedElement: bindedElements) {
    bindedElement.getText();
}
```

The application's code does not depend on the source HTML.
All the changes in the source HTML affect only on the form definition XML, so no recompilation is needed.
Also there is no need to change the code if the binding rules change.

For example, if *&lt;h1&gt;* and *&lt;h2&gt;* tags are added to the source HTML and we need to extract the text from this new tags too.
Then we need to change only the form definition XML as following:
```
<?xml version='1.0'?>
<ns1:form id='p-extractor' xmlns:ns1='http://d-shap.ru/schema/form-model/1.0'>
    <ns1:element id='p-element' lookup='h1' type='required+'/>
    <ns1:element id='p-element' lookup='h2' type='required+'/>
    <ns1:element id='p-element' lookup='p' type='required+'/>
</ns1:form>
```

Or as following:
```
<?xml version='1.0'?>
<ns1:form id='p-extractor' xmlns:ns1='http://d-shap.ru/schema/form-model/1.0'>
    <ns1:element id='p-element' lookup='h1, h2, p' type='required+'/>
</ns1:form>
```

If, for example, we need to extract the text not from all *&lt;p&gt;* tags, but from *&lt;p&gt;* tags with class *pclass*.
Then we need to change the form definition XML as following:
```
<?xml version='1.0'?>
<ns1:form id='p-extractor' xmlns:ns1='http://d-shap.ru/schema/form-model/1.0'>
    <ns1:element id='p-element' lookup='p.pclass' type='required+'/>
</ns1:form>
```

# XML definition
Namespace: ```http://d-shap.ru/schema/form-model/1.0```

## form
The top-level element. Defines the form.

Attributes:
* ```group``` - the form's group, optional
* ```id``` - the form's ID, mandatory

Attributes ```group``` and ```id``` identify the form and should be unique.

Child elements:
* ```element```
* ```single-element```
* ```form-reference```

## element
Element is a form part, that make sence for the application.

Attributes:
* ```id``` - the element's ID, optional
* ```lookup``` - the element's lookup string, used by the binding extension, mandatory
* ```type``` - the element's type, optional

Valid values for the ```type``` attribute depend on the parent element.
Possible values are:
* ```required``` - there should be exactly one element.
* ```required+``` - there should be at least one element.
* ```optional``` - there could be one element or no element at all.
* ```optional+``` - there could be more then one element or no element at all.
* ```prohibited``` - there should not be any element.

Child elements:
* ```attribute```
* ```element```
* ```single-element```
* ```form-reference```

## attribute
The element's attribute.

Attributes:
* ```id``` - the attribute's ID, optional
* ```lookup``` - the attribute's lookup string, used by the binding extension, mandatory
* ```type``` - the attribute's type, optional

Valid values for the ```type``` attribute depend on the parent element.
Possible values are:
* ```required``` - there should be exactly one element.
* ```optional``` - there could be one element or no element at all.
* ```prohibited``` - there should not be any element.

## single-element
Single element is a container for other elements.
Only one child element should present (but child element could be ```optional+```).

Attributes:
* ```id``` - the single element's ID, optional
* ```type``` - the single element's type, optional

Valid values for the ```type``` attribute depend on the parent element.
Possible values are:
* ```required``` - there should be exactly one element.
* ```optional``` - there could be one element or no element at all.
* ```prohibited``` - there should not be any element.

Child elements:
* ```element```
* ```single-element```

## form-reference
The reference to another form definition.
The elements of the referenced form are included in the current form as child elements of the ```form-reference``` element.

Attributes:
* ```group``` - the form's group, optional
* ```id``` - the form's ID, mandatory

# JSoup selectors
JSoup selectors are used in lookup attributes of the form definition.

[https://jsoup.org/cookbook/extracting-data/selector-syntax](https://jsoup.org/cookbook/extracting-data/selector-syntax)

## Selector overview
* `tagname`: find elements by tag, e.g. `a`
* `ns|tag`: find elements by tag in a namespace, e.g. `fb|name` finds `<fb:name>` elements
* `#id`: find elements by ID, e.g. `#logo`
* `.class`: find elements by class name, e.g. `.masthead`
* `[attribute]`: elements with attribute, e.g. `[href]`
* `[^attr]`: elements with an attribute name prefix, e.g. `[^data-]` finds elements with HTML5 dataset attributes
* `[attr=value]`: elements with attribute value, e.g. `[width=500]` (also quotable, like `[data-name='launch sequence']`)
* `[attr^=value]`, `[attr$=value]`, `[attr*=value]`: elements with attributes that start with, end with, or contain the value, e.g. `[href*=/path/]`
* `[attr~=regex]`: elements with attribute values that match the regular expression; e.g. `img[src~=(?i)\.(png|jpe?g)]`
* `*`: all elements, e.g. `*`

## Selector combinations
* `el#id`: elements with ID, e.g. `div#logo`
* `el.class`: elements with class, e.g. `div.masthead`
* `el[attr]`: elements with attribute, e.g. `a[href]`
* Any combination, e.g. `a[href].highlight`
* `ancestor child`: child elements that descend from ancestor, e.g. `.body p` finds `p` elements anywhere under a block with class `body`
* `parent > child`: child elements that descend directly from parent, e.g. `div.content > p` finds `p` elements; and `body > *` finds the direct children of the `body` tag
* `siblingA + siblingB`: finds sibling B element immediately preceded by sibling A, e.g. `div.head + div`
* `siblingA ~ siblingX`: finds sibling X element preceded by sibling A, e.g. `h1 ~ p`
* `el, el, el`: group multiple selectors, find unique elements that match any of the selectors; e.g. `div.masthead, div.logo`

## Pseudo selectors
* `:lt(n)`: find elements whose sibling index (i.e. its position in the DOM tree relative to its parent) is less than `n`; e.g. `td:lt(3)`
* `:gt(n)`: find elements whose sibling index is greater than `n`; e.g. `div p:gt(2)`
* `:eq(n)`: find elements whose sibling index is equal to `n`; e.g. form `input:eq(1)`
* `:has(selector)`: find elements that contain elements matching the selector; e.g. `div:has(p)`
* `:not(selector)`: find elements that do not match the selector; e.g. `div:not(.logo)`
* `:contains(text)`: find elements that contain the given text. The search is case-insensitive; e.g. `p:contains(jsoup)`
* `:containsOwn(text)`: find elements that directly contain the given text
* `:matches(regex)`: find elements whose text matches the specified regular expression; e.g. `div:matches((?i)login)`
* `:matchesOwn(regex)`: find elements whose own text matches the specified regular expression
* Note that the above indexed pseudo-selectors are 0-based, that is, the first element is at index 0, the second at 1, etc

# JavaScript DOM manipulations and AJAX-requests
In many cases the source HTML can not be obtained by one request to the server.
After the HTML page is loaded, JavaScript changes this HTML.
Also some parts of the HTML page are loaded with subsequent AJAX-requests (and again the JavaScript changes the HTML).
In this cases the final source HTML should be obtained first and then this final source HTML can be binded with the form model.

The final source HTML can be obtained with the headless Internet Browsers or with the real Internet Browsers.
**HtmlUnit** is an example of the headless Internet Browser.
To work with the real Internet Browsers **Selenium WebDriver** can be used.
Also some real Internet Browsers can work in headless mode.

## HtmlUnit example
```
String url = "some url";

WebClient webClient = new WebClient();
webClient.getOptions().setThrowExceptionOnScriptError(false);
HtmlPage page = webClient.getPage(url);
String pageAsText = page.getWebResponse().getContentAsString();
webClient.close();

Document document = formBinder.bindHtmlWithBaseUrl(pageAsText, url, "form-id");
```

## Selenium WebDriver example for Headless Google Chrome Browser
```
String url = "some url";

System.setProperty("webdriver.chrome.driver", "path/to/chrome/webdriver");
ChromeOptions chromeOptions = new ChromeOptions();
chromeOptions.setHeadless(true);
WebDriver webDriver = new ChromeDriver(chromeOptions);
webDriver.get(url);
Thread.sleep(1000);
String pageAsText = webDriver.getPageSource();
webDriver.quit();

Document document = formBinder.bindHtmlWithBaseUrl(pageAsText, url, "form-id");
```

## Form model selenium binding example
Form model selenium binding is the binding implementation for Selenium WebDriver.
It's primary purpose is the Internet Browser automation, but this binding also can be used to obtain the source HTML and bind the form model for HTML content extraction.
It is slower then form model html binding because additional processes are created (Internet Browser process, Selenium WebDriver process) and additional communication between this processes is needed.
```
// Load form definitions
FormDefinitions formDefinitions = new FormDefinitions();
File file = new File("file with the form definition");
FormDefinitionsLoader formDefinitionsLoader = new FormXmlDefinitionsFileLoader(file);
formDefinitionsLoader.load(formDefinitions);
SeleniumFormBinder formBinder = new SeleniumFormBinder(formDefinitions);

// Create WebDriver
System.setProperty("webdriver.chrome.driver", "path/to/chrome/webdriver");
ChromeOptions chromeOptions = new ChromeOptions();
chromeOptions.setHeadless(true);
WebDriver webDriver = new ChromeDriver(chromeOptions);

// Bind the HTML
String url = "some url";
webDriver.get(url);
Document document = formBinder.bind(webDriver, "p-extractor");

// Quit WebDriver
webDriver.quit();
```

# Frames and child windows
Sometimes frames and child windows are used.
In this case every frame and every child window should be bounded separately.
Neither the headless Internet Browsers nor the real Internet Browsers combine HTML from different frames or child windows.
To obtain the source HTML, context should be switched to the target frame or target child window.
```
WebDriver webDriver = ...
webDriver.get(url);
Thread.sleep(1000);
webDriver.switchTo().frame("nameOrId");
String pageAsText = webDriver.getPageSource();
webDriver.quit();

Document document = formBinder.bindHtmlWithBaseUrl(pageAsText, url, "form-id");
```

# HOW TO
[HOW TO examples](HOWTO.md)

# Latest release
* **&lt;groupId&gt;**: ru.d-shap
* **&lt;artifactId&gt;**: form-model-binding-html
* **&lt;version&gt;**: 1.0.0

# Donation
If you find my code useful, you can [bye me a coffee](https://www.paypal.me/dshapovalov)
