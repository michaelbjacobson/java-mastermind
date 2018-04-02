$(document).ready(function() {

    let arrowIncrement = '+=8.5vh';
    let colors = 'gold cyan magenta blue purple orange';
    let colorToInt = { 'gold': 0, 'cyan': 1, 'magenta': 2, 'blue': 3, 'purple': 4, 'orange': 5 };
    let intToColor = { 0: 'gold', 1: 'cyan', 2: 'magenta', 3: 'blue', 4: 'purple', 5: 'orange' };
    let turnCounter = 1;
    let pegCounter = 1;
    let guess = '';

    function isCorrect(keyPeg) {
        return keyPeg === 'correct';
    }

    function setEndOfGameControls() {
        $('#new_game').addClass('emphasise', 1000);
        $('#controls').find('div, button')
            .not("#new_game")
            .addClass('disabled');
    }

    function nextTurn() {
        turnCounter++;
        pegCounter = 1;
        guess = '';
        $('.arrow').css( 'top', arrowIncrement );
    }

    function showSolution() {
        $.get( '/solution' ).done(function(response) {
            for (let i = 0; i < 5; i++) {
                $(`#solution`).find(`.solution_peg.${i}`)
                    .addClass(intToColor[ parseInt(response[i]) ])
                    .addClass('visible');
            }
            setEndOfGameControls();
        });
    }

    let $controls = $('#controls');

    $controls.on('click', '.color' , function() {
        if ( !$(this).hasClass('disabled') ) {
            if (pegCounter <= 4) {
                let color = $(this).attr('class').split(' ')[1];
                let $activePeg = $(`.guess_row.${turnCounter}`).find(`.code_peg.${pegCounter}`);
                guess += colorToInt[color];
                $activePeg.addClass(color);
                pegCounter++;
            }
        }
    });

    $controls.on('click', '#submit' , function() {
        if ( !$(this).hasClass('disabled') ) {
            if (guess.length === 4) {
                $.post( '/guess', { guess } ).done(function(response) {
                    let formattedResponse = response.split(',');

                    for (let i = 0; i < 4; i++) {
                        $(`.guess_row.${turnCounter}`).find(`.key_peg.${i}`)
                            .addClass(formattedResponse[i]);
                    }

                    if ( formattedResponse.length === 4 && formattedResponse.every(isCorrect) ) {
                        showSolution();
                        alert('You cracked it!');
                    } else if (turnCounter === 10) {
                        showSolution();
                        alert('Game over!');
                    } else {
                        nextTurn();
                    }

                });
            }
        }
    });

    $controls.on('click', '#clear' , function() {
        if ( !$(this).hasClass('disabled') ) {
            $(`.guess_row.${turnCounter}`).find('.code_peg').removeClass(colors);
            pegCounter = 1;
            guess = '';
        }
    });

    $controls.on('click', '#new_game' , function() {
        location.reload(true);
    });

    $controls.on('click', '#solve' , function() {
        if ( !$(this).hasClass('disabled') ) {
            showSolution();
        }
    });

});
