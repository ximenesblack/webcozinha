document.addEventListener("DOMContentLoaded", () => {

    const forms = document.querySelectorAll("form");

    forms.forEach(form => {

        form.addEventListener("submit", (event) => {

            const botao = form.querySelector("button");

            botao.innerText = "Processando...";
            botao.disabled = true;

        });

    });

});