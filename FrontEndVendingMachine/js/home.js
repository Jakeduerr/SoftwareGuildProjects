$(document).ready(function () {

    $('#input-divs').css('text-align', 'center').css('background-color', 'lightblue').css('border-style', 'solid').css('border-width', '4px');
    $('body').css('font-family', 'merriweather');
    $('label').css('margin', '18px').css('text-align', 'center').css('font-size', '22px');
    $('input').css('margin', '18px').css('text-align', 'center').css('font-size', '15px');
    $('button').css('margin', '10px');
    $('h1').css('text-align', 'center');
    $('hr').css('margin', '10px').css('color', 'black');

    loadItems();

    var totalMoney = 0;
    $('#total-money-in').val(totalMoney);

    $('#add-dollar').click(function (event) {
        totalMoney += 1;
        $('#total-money-in').val(totalMoney.toFixed(2));
    });

    $('#add-quarter').click(function (event) {
        totalMoney += .25;
        $('#total-money-in').val(totalMoney.toFixed(2));
    });

    $('#add-dime').click(function (event) {
        totalMoney += .10;
        $('#total-money-in').val(totalMoney.toFixed(2));
    });

    $('#add-nickel').click(function (event) {
        totalMoney += .05;
        $('#total-money-in').val(totalMoney.toFixed(2));
    });

    $('#makePurchase').click(function (event) {
        var id = $('#items').val();
        totalMoney = $('#total-money-in').val();

        $.ajax({
            type: 'GET',
            url: 'http://localhost:8080/money/' + totalMoney + '/item/' + id,
            success: function (data, status) {
                var quarters;
                var dimes;
                var nickels;
                var pennies;
                quarters = data.quarters;
                dimes = data.dimes;
                nickels = data.nickels;
                pennies = data.pennies;

                $('#change').val('Quarters: ' + quarters + ' Dimes: ' + dimes + ' Nickels: ' + nickels + ' Pennies: ' + pennies);

                $('#messages').val('Thank You!!!');

            },
            error: function (xhr, status) {
                var errorArray = xhr.responseText;
                var errorString = JSON.parse(errorArray);
                $('#messages').val(errorString.message);
            }
        })
    })

    $('#returnChange').click(function (event) {

        if ($('#items').val() == '' && totalMoney > 0) {
            var quarters;
            var dimes;
            var nickels;
            var pennies;

            var newTotalMoney = totalMoney * 100;
            quarters = Math.floor(newTotalMoney / 25);
            var a = newTotalMoney - (25 * quarters);
            dimes = Math.floor(a / 10);
            var b = a - (10 * dimes);
            nickels = Math.floor(b / 5);
            var c = b - (5 * nickels);
            pennies = Math.floor(c / 1);

            $('#change').val('Quarters: ' + quarters + ' Dimes: ' + dimes + ' Nickels: ' + nickels + ' Pennies: ' + pennies);
            totalMoney = 0;
        } else {
            $('#change').val('');
        }

        $('#total-money-in').val('');
        $('#messages').val('');
        $('#items').val('');

        loadItems();
        totalMoney = 0;
    })
});

function loadItems() {
    clearItemDiv();
    var itemDiv = $('#item-div');

    $.ajax({
        type: 'GET',
        url: 'http://localhost:8080/items',
        success: function (itemsArray) {
            $.each(itemsArray, function (index, item) {
                var id = item.id;
                var name = item.name;
                var price = item.price;
                var quantity = item.quantity;

                var buttons = ' <button class="col-md-4 btn btn-default"  ' + ' onclick="itemButtons(' + id + ')" '
                    + 'style="height: 266px; border-style: solid; border-width: 2px; border-color: black; text-align: center;'
                    + 'font-size: 22px; background-color: rgba(253, 81, 81, 0.836);">';
                buttons += 'Item #' + id + '<br/>';
                buttons += name + '<br/>';
                buttons += '$' + price + '<br/>';
                buttons += 'Inventory: ' + quantity + '<br/>';
                buttons += '</button>';

                itemDiv.append(buttons);

            });
        },
        error: function () {
            $('#errorMessages')
                .append($('<li>')
                    .attr({ class: 'list-group-item list-group-item-danger' })
                    .text('Error calling web service. Please try again later.'));
        }
    });
}

function itemButtons(id) {
    $('#items').val(id);
};

function clearItemDiv() {
    $('#item-div').empty();
}
