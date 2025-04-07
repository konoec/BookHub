// Variables para mantener el estado
let detailIndex = 0;
const TAX_RATE = 0.18;

// Inicialización cuando el documento está listo
$(document).ready(function() {
    // Inicializar Select2 para el selector de cliente con tema Bootstrap
    $('#customer').select2({
        placeholder: 'Seleccione un cliente',
        width: '100%',
        theme: 'bootstrap'
    });

    // Agregar primera fila de detalle
    addDetailRow();

    // Calcular totales iniciales
    calculateTotals();
});

// Función para agregar una nueva fila de detalle
function addDetailRow() {
    const template = document.querySelector('#detail-row');
    const clone = template.content.cloneNode(true);

    // Actualizar índices
    updateIndexes(clone, detailIndex);

    // Agregar al contenedor
    document.querySelector('#detail-items').appendChild(clone);

    // Inicializar Select2 para el nuevo selector de libro
    $(`#book-${detailIndex}`).select2({
        placeholder: 'Seleccione un libro',
        width: '100%',
        theme: 'bootstrap'
    }).on('change', function() {
        const selectedOption = $(this).find(':selected');
        const stock = selectedOption.data('stock');
        const price = selectedOption.data('price');

        // Mostrar información del stock disponible
        if (stock !== undefined) {
            const stockInfo = $(`<small class="text-muted">Stock disponible: ${stock} unidades</small>`);
            $(this).parent().find('.stock-info').remove();
            $(this).parent().append(stockInfo);
        }

        updatePriceAndSubtotal(detailIndex);
    });

    // Incrementar contador
    detailIndex++;
}

// Función para remover una fila de detalle
function removeDetailRow(button) {
    const detailRow = $(button).closest('.detail-row');

    // Animación de fadeOut antes de remover
    detailRow.fadeOut(300, function() {
        $(this).remove();
        calculateTotals();

        // Mostrar alerta si no hay detalles
        if ($('.detail-row').length === 0) {
            addDetailRow();
        }
    });
}

// Función para actualizar índices en los elementos clonados
function updateIndexes(element, index) {
    // Actualizar nombres e IDs
    element.querySelector('.book-select').name = `details[${index}].book.id`;
    element.querySelector('.book-select').id = `book-${index}`;

    element.querySelector('.quantity').name = `details[${index}].quantity`;
    element.querySelector('.quantity').id = `quantity-${index}`;

    element.querySelector('.unit-price').name = `details[${index}].unitPrice`;
    element.querySelector('.unit-price').id = `unit-price-${index}`;

    element.querySelector('.subtotal').name = `details[${index}].subtotal`;
    element.querySelector('.subtotal').id = `subtotal-${index}`;

    // Agregar event listeners
    element.querySelector('.quantity').addEventListener('change', function() {
        const quantity = parseInt(this.value);
        const stock = parseInt($(`#book-${index} option:selected`).data('stock'));

        // Validar stock al cambiar cantidad
        if (quantity > stock) {
            alert(`La cantidad excede el stock disponible (${stock} unidades)`);
            this.value = stock;
        }
        updateSubtotal(index);
    });
}

// Función para actualizar precio y subtotal cuando se selecciona un libro
function updatePriceAndSubtotal(index) {
    const bookSelect = $(`#book-${index}`);
    const unitPrice = bookSelect.find(':selected').data('price') || 0;
    const quantity = $(`#quantity-${index}`).val();

    $(`#unit-price-${index}`).val(unitPrice.toFixed(2));
    updateSubtotal(index);
}

// Función para actualizar subtotal de una línea
function updateSubtotal(index) {
    const quantity = parseFloat($(`#quantity-${index}`).val()) || 0;
    const unitPrice = parseFloat($(`#unit-price-${index}`).val()) || 0;
    const subtotal = quantity * unitPrice;

    $(`#subtotal-${index}`).val(subtotal.toFixed(2));
    calculateTotals();
}

// Función para calcular totales
function calculateTotals() {
    let subtotal = 0;

    // Sumar subtotales
    $('.subtotal').each(function() {
        subtotal += parseFloat($(this).val()) || 0;
    });

    const taxAmount = subtotal * TAX_RATE;
    const total = subtotal + taxAmount;

    // Actualizar campos con animación
    animateValue('#subtotal', subtotal);
    animateValue('#taxAmount', taxAmount);
    animateValue('#totalAmount', total);
}

// Función para animar cambios en valores
function animateValue(selector, newValue) {
    const element = $(selector);
    const oldValue = parseFloat(element.val()) || 0;
    $({value: oldValue}).animate({value: newValue}, {
        duration: 300,
        step: function() {
            element.val(this.value.toFixed(2));
        },
        complete: function() {
            element.val(newValue.toFixed(2));
        }
    });
}

// Validación antes de enviar el formulario
$('form').on('submit', function(e) {
    e.preventDefault();

    // Validar que haya al menos un detalle
    if ($('.detail-row').length === 0) {
        alert('Debe agregar al menos un libro a la venta');
        return false;
    }

    // Validar que todos los campos requeridos estén llenos
    let isValid = true;
    $('.detail-row').each(function() {
        const bookId = $(this).find('.book-select').val();
        const quantity = parseInt($(this).find('.quantity').val());

        if (!bookId || !quantity) {
            alert('Por favor complete todos los campos requeridos');
            isValid = false;
            return false;
        }

        const stock = parseInt($(this).find('.book-select option:selected').data('stock'));
        if (quantity > stock) {
            alert(`Stock insuficiente para el libro seleccionado (disponible: ${stock})`);
            isValid = false;
            return false;
        }
    });

    if (isValid) {
        this.submit();
    }
});