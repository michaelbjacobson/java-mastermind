<!DOCTYPE html>
<html>
<head>
    <title>Java Mastermind</title>

    <link href="https://fonts.googleapis.com/css?family=Source+Sans+Pro:200,300,400,600,700" rel="stylesheet">
    <link type="text/css" rel="stylesheet" href="stylesheet.css">
    <link rel="stylesheet" href="https://ajax.googleapis.com/ajax/libs/jqueryui/1.12.1/themes/smoothness/jquery-ui.css">

    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <script src="https://ajax.googleapis.com/ajax/libs/jqueryui/1.12.1/jquery-ui.min.js"></script>
    <script type="text/javascript" rel="script" src="script.js"></script>
</head>
<body>

<div class="arrow"></div>

<div id="controls">
    <div class="color gold"></div>
    <div class="color cyan"></div>
    <div class="color magenta"></div>
    <br>
    <div class="color blue"></div>
    <div class="color purple"></div>
    <div class="color orange"></div>
    <br><br>
    <button id="submit">SUBMIT</button>
    <button id="clear">CLEAR</button>
    <br>
    <button id="new_game">NEW GAME</button>
    <button id="solve">SOLVE</button>
</div>

<div id="solution">
    <p>SOLUTION</p>
    <div class="solution_peg 0"></div>
    <div class="solution_peg 1"></div>
    <div class="solution_peg 2"></div>
    <div class="solution_peg 3"></div>
</div>

<div id="decoding_board">

    <#list 1..11 as i >
        <div class="guess_row ${i}">
            <div class="guess">
                <div class="code_peg 1"></div>
                <div class="code_peg 2"></div>
                <div class="code_peg 3"></div>
                <div class="code_peg 4"></div>
            </div>
            <div class="feedback">
                <div class="key_pegs">
                    <div class="key_peg 0"></div>
                    <div class="key_peg 1"></div>
                    <div class="key_peg 2"></div>
                    <div class="key_peg 3"></div>
                </div>
            </div>
        </div>
    </#list>

</div>

</body>
</html>