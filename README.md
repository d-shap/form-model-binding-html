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

A selector is a chain of simple selectors, separated by combinators.
Selectors are case insensitive (including against elements, attributes, and attribute values).

## Selectors
|Pattern|Matches|Example|
|-------|-------|-------|
|`*`|any element|`*`|
|`tag`|elements with the given tag name|`div`|
|`*\|E`|elements of type E in any namespace ns|`*\|name` finds &lt;fb:name&gt; elements|
|`ns\|E`|elements of type E in the namespace ns|`fb\|name` finds &lt;fb:name&gt; elements|
|`#id`|elements with attribute ID of "id"|`div#wrap`, `#logo`|
|`.class`|elements with a class name of "class"|`div.left`, `.result`|
|`[attr]`|elements with an attribute named "attr" (with any value)|`a[href]`, `[title]`|
|`[^attrPrefix]`|elements with an attribute name starting with "attrPrefix"|`[^data-]`, `div[^data-]`|
|`[attr=val]`|elements with an attribute named "attr", and value equal to "val"|`img[width=500]`, `a[rel=nofollow]`|
|`[attr="val"]`|elements with an attribute named "attr", and value equal to "val"|`span[hello="Cleveland"][goodbye="Columbus"]`, `a[rel="nofollow"]`|
|`[attr^=valPrefix]`|elements with an attribute named "attr", and value starting with "valPrefix"|`a[href^=http:]`|
|`[attr$=valSuffix]`|elements with an attribute named "attr", and value ending with "valSuffix"|`img[src$=.png]`|
|`[attr*=valContaining]`|elements with an attribute named "attr", and value containing "valContaining"|`a[href*=/search/]`|
|`[attr~=regex]`|elements with an attribute named "attr", and value matching the regular expression|`mg[src~=(?i)\\.(png\|jpe?g)]`|
| |The above may be combined in any order|`div.header[title]`|

## Combinators
|Pattern|Matches|Example|
|-------|-------|-------|
|`E F`|an F element descended from an E element|`div a`, `.logo h1`|
|`E > F`|an F direct child of E|`ol > li`|
|`E + F`|an F element immediately preceded by sibling E|`li + li`, `div.head + div`|
|`E ~ F`|an F element preceded by sibling E|`h1 ~ p`|
|`E, F, G`|all matching elements E, F, or G|`a[href], div, h3`|

## Pseudo selectors
|Pattern|Matches|Example|
|-------|-------|-------|
|`:lt(n)`|elements whose sibling index is less than `n`|`td:lt(3)` finds the first 3 cells of each row|
|`:gt(n)`|elements whose sibling index is greater than `n`|`td:gt(1)` finds cells after skipping the first two|
|`:eq(n)`|elements whose sibling index is equal to `n`|`td:eq(0)` finds the first cell of each row|
|`:has(selector)`|elements that contains at least one element matching the `selector`|`div:has(p)` finds divs that contain p elements|
|`:not(selector)`|elements that do not match the selector|`div:not(.logo)` finds all divs that do not have the "logo" class, `div:not(:has(div))` finds divs that do not contain divs|
|`:contains(text)`|elements that contains the specified text|`p:contains(jsoup)` finds p elements containing the text "jsoup"|
|`:matches(regex)`|elements whose text matches the specified regular expression|`td:matches(\\d+)` finds table cells containing digits, `div:matches((?i)login)` finds divs containing the text, case insensitively|
|`:containsOwn(text)`|elements that directly contain the specified text|`p:containsOwn(jsoup)` finds p elements with own text "jsoup"|
|`:matchesOwn(regex)`|elements whose own text matches the specified regular expression|`td:matchesOwn(\\d+)` finds table cells directly containing digits, `div:matchesOwn((?i)login)` finds divs containing the text, case insensitively|
|`:containsData(data)`|elements that contains the specified data. The contents of script and style elements, and comment nodes (etc) are considered data nodes, not text nodes|`script:contains(jsoup)` finds script elements containing the data "jsoup"|
| |The above may be combined in any order and with other selectors|`.light:contains(name):eq(0)`|
* The above indexed pseudo-selectors are 0-based, that is, the first element is at index 0, the second at 1, etc
* The above text pseudo-selectors are case insensitive

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
In this case every frame and every child window should be binded separately.
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
Form model library:
* **&lt;groupId&gt;**: ru.d-shap
* **&lt;artifactId&gt;**: form-model
* **&lt;version&gt;**: 1.0

Form model html binding:
* **&lt;groupId&gt;**: ru.d-shap
* **&lt;artifactId&gt;**: form-model-binding-html
* **&lt;version&gt;**: 1.0.0

# Donation
If you find my code useful, you can [bye me a coffee](https://www.paypal.me/dshapovalov)
