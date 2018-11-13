HOW TO ...
==========

... bind elements
-----------------
HTML:
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

Form definition:
```
<?xml version="1.0"?>
<ns1:form group="bindElements-01" id="form-01" xmlns:ns1="http://d-shap.ru/schema/form-model/1.0">
    <ns1:element id="elementId" lookup="p" type="required+" />
</ns1:form>
```

Result:
```
Some text.
Some other text.
Some more text.
```

... bind elements with the same id, example 1
---------------------------------------------
HTML:
```
<html>
<head>
    <title>Test page</title>
</head>
<body>
<h1>Header 1</h1>
<h2>Header 1.1</h2>
<p>Some text.</p>
<p>Some other text.</p>
<h2>Header 1.2</h2>
<p>Some more text.</p>
</body>
</html>
```

Form definition:
```
<?xml version="1.0"?>
<ns1:form group="bindElements-02" id="form-01" xmlns:ns1="http://d-shap.ru/schema/form-model/1.0">
    <ns1:element id="elementId" lookup="h1" type="required+" />
    <ns1:element id="elementId" lookup="h2" type="required+" />
    <ns1:element id="elementId" lookup="p" type="required+" />
</ns1:form>
```

Result:
```
Header 1
Header 1.1
Header 1.2
Some text.
Some other text.
Some more text.
```

... bind elements with the same id, example 2
---------------------------------------------
HTML:
```
<html>
<head>
    <title>Test page</title>
</head>
<body>
<h1>Header 1</h1>
<h2>Header 1.1</h2>
<p>Some text.</p>
<p>Some other text.</p>
<h2>Header 1.2</h2>
<p>Some more text.</p>
</body>
</html>
```

Form definition:
```
<?xml version="1.0"?>
<ns1:form group="bindElements-02" id="form-02" xmlns:ns1="http://d-shap.ru/schema/form-model/1.0">
    <ns1:element id="elementId" lookup="h1, h2, p" type="required+" />
</ns1:form>
```

Result:
```
Header 1
Header 1.1
Some text.
Some other text.
Header 1.2
Some more text.
```

... bind elements with class
----------------------------
HTML:
```
<html>
<head>
    <title>Test page</title>
</head>
<body>
<p class="qclass">Some text.</p>
<p class="pclass">Some other text.</p>
<p class="qclass pclass">Some more text.</p>
</body>
</html>
```

Form definition:
```
<?xml version="1.0"?>
<ns1:form group="bindElements-03" id="form-01" xmlns:ns1="http://d-shap.ru/schema/form-model/1.0">
    <ns1:element id="elementId" lookup="p.pclass" type="required+" />
</ns1:form>
```

Result:
```
Some other text.
Some more text.
```

... bind child elements, example 1
----------------------------------
HTML:
```
<html>
<head>
    <title>Test page</title>
</head>
<body>
<table>
    <tr>
        <td>Row 1 column 1</td>
        <td>Row 1 column 2</td>
        <td>Row 1 column 3</td>
    </tr>
    <tr>
        <td>Row 2 column 1</td>
        <td>Row 2 column 2</td>
        <td>Row 2 column 3</td>
    </tr>
    <tr>
        <td>Row 3 column 1</td>
        <td>Row 3 column 2</td>
        <td>Row 3 column 3</td>
    </tr>
</table>
</body>
</html>
```

Form definition:
```
<?xml version="1.0"?>
<ns1:form group="bindElements-04" id="form-01" xmlns:ns1="http://d-shap.ru/schema/form-model/1.0">
    <ns1:element lookup="tr" type="required+">
        <ns1:element id="elementId" lookup="td:eq(1)" />
    </ns1:element>
</ns1:form>
```

Result:
```
Row 1 column 2
Row 2 column 2
Row 3 column 2
```

... bind child elements, example 2
----------------------------------
HTML:
```
<html>
<head>
    <title>Test page</title>
</head>
<body>
<table>
    <tr>
        <td>Row 1 column 1</td>
        <td>Row 1 column 2</td>
        <td>Row 1 column 3</td>
    </tr>
    <tr>
        <td>Row 2 column 1</td>
        <td>Row 2 column 2</td>
        <td>Row 2 column 3</td>
    </tr>
    <tr>
        <td>Row 3 column 1</td>
        <td>Row 3 column 2</td>
        <td>Row 3 column 3</td>
    </tr>
</table>
</body>
</html>
```

Form definition:
```
<?xml version="1.0"?>
<ns1:form group="bindElements-04" id="form-02" xmlns:ns1="http://d-shap.ru/schema/form-model/1.0">
    <ns1:element id="elementId" lookup="tr > td:eq(1)" type="required+" />
</ns1:form>
```

Result:
```
Row 1 column 2
Row 2 column 2
Row 3 column 2
```

... bind toggle elements, example 1
----------------------------------
HTML:
```
<html>
<head>
    <title>Test page</title>
</head>
<body>
<div>
    <img src="someurl"/>
    <br/>
    <div>
        <span>View count</span>
        <span class="like">Like text</span>
        <span class="dislike">Dislike text</span>
        <span class="subscribe">Subscribe text</span>
    </div>
</div>
</body>
</html>
```

Form definition:
```
<?xml version="1.0"?>
<ns1:form group="bindSingleElement-01" id="form-01" xmlns:ns1="http://d-shap.ru/schema/form-model/1.0">
    <ns1:single-element id="elementId">
        <ns1:element lookup=".subscribe" />
        <ns1:element lookup=".unsubscribe" />
    </ns1:single-element>
</ns1:form>
```

Result:
```
Subscribe text
```

... bind toggle elements, example 2
----------------------------------
HTML:
```
<html>
<head>
    <title>Test page</title>
</head>
<body>
<div>
    <img src="someurl"/>
    <br/>
    <div>
        <span>View count</span>
        <span class="like">Like text</span>
        <span class="dislike">Dislike text</span>
        <span class="unsubscribe">Unsubscribe text</span>
    </div>
</div>
</body>
</html>
```

Form definition:
```
<?xml version="1.0"?>
<ns1:form group="bindSingleElement-01" id="form-01" xmlns:ns1="http://d-shap.ru/schema/form-model/1.0">
    <ns1:single-element id="elementId">
        <ns1:element lookup=".subscribe" />
        <ns1:element lookup=".unsubscribe" />
    </ns1:single-element>
</ns1:form>
```

Result:
```
Unsubscribe text
```
