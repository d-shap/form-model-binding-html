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
<ns1:form group="bindElement-01" id="form-01" xmlns:ns1="http://d-shap.ru/schema/form-model/1.0">
    <ns1:element id="resultId" lookup="p" type="required+" />
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
<ns1:form group="bindElement-02" id="form-01" xmlns:ns1="http://d-shap.ru/schema/form-model/1.0">
    <ns1:element id="resultId" lookup="h1" type="required+" />
    <ns1:element id="resultId" lookup="h2" type="required+" />
    <ns1:element id="resultId" lookup="p" type="required+" />
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
<ns1:form group="bindElement-02" id="form-02" xmlns:ns1="http://d-shap.ru/schema/form-model/1.0">
    <ns1:element id="resultId" lookup="h1, h2, p" type="required+" />
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
<ns1:form group="bindElement-03" id="form-01" xmlns:ns1="http://d-shap.ru/schema/form-model/1.0">
    <ns1:element id="resultId" lookup="p.pclass" type="required+" />
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
<ns1:form group="bindElement-04" id="form-01" xmlns:ns1="http://d-shap.ru/schema/form-model/1.0">
    <ns1:element lookup="tr" type="required+">
        <ns1:element id="resultId" lookup="td:eq(1)" />
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
<ns1:form group="bindElement-04" id="form-02" xmlns:ns1="http://d-shap.ru/schema/form-model/1.0">
    <ns1:element id="resultId" lookup="tr > td:eq(1)" type="required+" />
</ns1:form>
```

Result:
```
Row 1 column 2
Row 2 column 2
Row 3 column 2
```

... bind toggle elements, example 1
-----------------------------------
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
    <ns1:single-element id="resultId">
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
-----------------------------------
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
    <ns1:single-element id="resultId">
        <ns1:element lookup=".subscribe" />
        <ns1:element lookup=".unsubscribe" />
    </ns1:single-element>
</ns1:form>
```

Result:
```
Unsubscribe text
```

... bind form reference
-----------------------
HTML:
```
<html>
<head>
    <title>Test page</title>
</head>
<body>
<div class="imgblock item1">
    <a href="viewurl1"> <img src="someurl1"/></a>
    <br/>
    <div class="description">Description 1</div>
    <div>
        <span>View count</span>
        <span class="like">Like text</span>
        <span class="dislike">Dislike text</span>
    </div>
</div>
<div class="imgblock item2">
    <a href="viewurl2"> <img src="someurl2"/></a>
    <br/>
    <div class="description">Description 2</div>
    <div>
        <span>View count</span>
        <span class="like">Like text</span>
        <span class="dislike">Dislike text</span>
    </div>
</div>
<div class="imgblock item3">
    <a href="viewurl3"> <img src="someurl3"/></a>
    <br/>
    <div class="description">Description 3</div>
    <div>
        <span>View count</span>
        <span class="like">Like text</span>
        <span class="dislike">Dislike text</span>
    </div>
</div>
</body>
</html>
```

Form definition:
```
<?xml version="1.0"?>
<ns1:form group="bindFormReference-01" id="form-01" xmlns:ns1="http://d-shap.ru/schema/form-model/1.0">
    <ns1:element lookup="div.imgblock" type="optional+">
        <ns1:form-reference group="bindFormReference-01" id="form-02" />
    </ns1:element>
</ns1:form>
```
```
<?xml version="1.0"?>
<ns1:form group="bindFormReference-01" id="form-02" xmlns:ns1="http://d-shap.ru/schema/form-model/1.0">
    <ns1:element lookup="a">
        <ns1:element lookup="img" />
    </ns1:element>
    <ns1:element id="resultId" lookup="div.description" />
    <ns1:element lookup="div:has(span.like) span:eq(0)" />
</ns1:form>
```

Result:
```
Description 1
Description 2
Description 3
```

... bind attribute
------------------
HTML:
```
<html>
<head>
    <title>Test page</title>
</head>
<body>
<div class="imgblock item1">
    <a href="viewurl1"> <img src="someurl1"/></a>
    <br/>
    <div class="description">Description 1</div>
</div>
<div class="imgblock item2">
    <a href="viewurl2"> <img src="someurl2"/></a>
    <br/>
    <div class="description">Description 2</div>
</div>
<div class="imgblock item3">
    <a href="viewurl3"> <img src="someurl3"/></a>
    <br/>
    <div class="description">Description 3</div>
</div>
</body>
</html>
```

Form definition:
```
<?xml version="1.0"?>
<ns1:form group="bindAttribute-01" id="form-01" xmlns:ns1="http://d-shap.ru/schema/form-model/1.0">
    <ns1:element lookup="a" type="optional+">
        <ns1:attribute id="resultId" lookup="href" />
    </ns1:element>
    <ns1:element lookup="img" type="optional+">
        <ns1:attribute id="resultId" lookup="src" />
    </ns1:element>
</ns1:form>
```

Result:
```
viewurl1
viewurl2
viewurl3
someurl1
someurl2
someurl3
```
