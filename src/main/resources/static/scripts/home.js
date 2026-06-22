document.addEventListener('DOMContentLoaded', () => {
    const cards = document.querySelectorAll('.glass-card, section.animate-fade-up');

    cards.forEach((card, index) => {
        card.style.animationDelay = `${index * 0.04}s`;
    });
});