

// Mostrar y ocultar sibdebar

const menuBtn = document.querySelector('#menu-btn');
const closeBtn = document.querySelector('#close-btn');
const sidebar = document.querySelector('aside');

menuBtn.addEventListener('click', () => {
    sidebar.style.display = 'block';
})

closeBtn.addEventListener('click', () => {
    sidebar.style.display = 'none';
})

// Tema oscuro
const themeBtn = document.querySelector('.theme-btn');

function aplicarTemaGuardado() {
    const darkThemeEnabled = localStorage.getItem('dark-theme') === 'enabled';
    if (darkThemeEnabled) {
        document.body.classList.add('dark-theme');
        themeBtn.querySelector('i:first-child').classList.remove('active');
        themeBtn.querySelector('i:last-child').classList.add('active');
    }
}

aplicarTemaGuardado();

themeBtn.addEventListener('click', () => {
    document.body.classList.toggle('dark-theme');
    themeBtn.querySelector('i:first-child').classList.toggle('active');
    themeBtn.querySelector('i:last-child').classList.toggle('active');
    if (document.body.classList.contains('dark-theme')) {
        localStorage.setItem('dark-theme', 'enabled');
    } else {
        localStorage.setItem('dark-theme', 'disabled');
    }
});
