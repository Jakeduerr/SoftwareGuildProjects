$(document).ready(function () {

    loadItems();

    $('#input-divs').css('text-align', 'center').css('background-color', 'lightblue').css('border-style', 'solid');
    $('body').css('font-family', 'merriweather');
    $('label').css('margin', '18px').css('text-align', 'center').css('font-size', '22px');
    $('input').css('margin', '18px').css('text-align', 'center');
    $('button').css('margin', '8px');
    $('h1').css('text-align', 'center');
    $('hr').css('margin', '.5px').css('color', 'black');

    var totalMoney = 0;
    $('#total-money-in').val(totalMoney);

    $('#add-dollar').click(function (event) {
        totalMoney + 1;

    });

    $('#add-quarter').click(function (event) {
        totalMoney + .25;

    });

    $('#add-dime').click(function (event) {
        totalMoney + .10;

    });

    $('#add-nickel').click(function (event) {
        totalMoney + .05;

    });

    $('#makePurchase').click(function (event) {

        $.ajax({
            type: 'GET',
            url: 'http://localhost:8080/money/' + totalMoney + '/item/' + id,
            success: function (data, status) {
                var choosenItem;
                choosenItem.val(data.id);
                choosenItem.val(data.name);
                choosenItem.val(data.price);
                choosenItem.val(data.quantity);

            },
            error: function () {
                $('#errorMessages')
                    .append($('<li>')
                        .attr({ class: 'list-group-item list-group-item-danger' })
                        .text('Error calling web service. Please try again later.'));
            }
        })

        


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

                var div = '<div class="col-md-4" style="height: 266px; border-style: solid; border-width: 2px; text-align: center; font-size: 20px; background-color: rgba(253, 81, 81, 0.836);">';
                div += '<br/>';
                div += '<br/>';
                div += 'Item #' + id + '<br/>';
                div += name + '<br/>';
                div += '$' + price + '<br/>';
                div += 'Inventory: ' + quantity + '<br/>';
                div += '</div>';

                itemDiv.append(div);

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

function clearItemDiv() {
    $('#item-div').empty();
}
