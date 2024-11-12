const template = document.createElement('template')
template.innerHTML = `
    <style>
        .card {
            --font-color: #323232;
            --font-color-sub: #666;
            --bg-color: #fff;
            --main-color: #323232;
            --main-focus: #2d8cf0;
            width: 345px;
            height: 385px;
            background: var(--bg-color);
            border: 2px solid var(--main-color);
            box-shadow: 4px 4px var(--main-color);
            border-radius: 5px;
            display: flex;
            flex-direction: column;
            justify-content: flex-start;
            padding: 10px;
            gap: 5px;
        }

        .card:last-child {
        justify-content: flex-start;
        }

        .card-img {
        transition: all 0.5s;
        display: flex;
        justify-content: center;
        margin-bottom: -10px;
        }

        .card-img img {
        height: 220px;
        width: 200px;
        object-fit: cover;
        }

        .card-title {
        font-size: 18px;
        font-weight: 500;
        text-align: center;
        color: var(--font-color);
        margin: 5px 0;
        }

        .card-subtitle {
        font-size: 13px;
        font-weight: 400;
        color: var(--font-color-sub);
        margin-bottom: 5px;
        }

        .color-size-container {
        display: flex;
        justify-content: space-between;
        font-size: 0.8rem;
        color: #000;
        gap: 0.5rem;
        }

        .color-size-container > * {
        flex: 1;
        }

        .card-divider {
        width: 100%;
        border: 1px solid var(--main-color);
        border-radius: 50px;
        }

        .card-footer {
        display: flex;
        flex-direction: row;
        justify-content: space-between;
        align-items: center;
        }

        .card-price {
        font-size: 18px;
        font-weight: 500;
        color: var(--font-color);
        }

        .card-price span {
        font-size: 18px;
        font-weight: 500;
        color: var(--font-color-sub);
        }

        .card-btn {
        height: 32px;
        background: var(--bg-color);
        border: 2px solid var(--main-color);
        border-radius: 5px;
        padding: 0 12px;
        transition: all 0.3s;
        }

        .card-btn svg {
        width: 100%;
        height: 100%;
        fill: var(--main-color);
        transition: all 0.3s;
        }

        .card-img:hover {
        transform: translateY(-3px);
        }

        .card-btn:hover {
        border: 2px solid var(--main-focus);
        }

        .card-btn:hover svg {
        fill: var(--main-focus);
        }
        p{
        font-size: 13px;
        }
    </style>
    <div class="card">
        <div class="card-img">
            <img src="urlImg" alt="nombrePrenda">
        </div>
        <div class="card-title" id="nombrePrenda"></div>
        <div class="card-subtitle" id="descripcionPrenda"></div>
        <div class="color-size-container">
            <p> <strong>Color: </strong><span id="color"></span></p>
            <p> <strong>Talla: </strong><span id="talla"></span></p>
            <p> <strong>Stock: </strong><span id="stock"></span></p>
        </div>
        <hr class="card-divider">
        <div class="card-footer">
            <div class="card-price"><span>$</span> <strong id="precio"></strong></div>
            <button class="card-btn">
            <svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 512 512"><path d="m397.78 316h-205.13a15 15 0 0 1 -14.65-11.67l-34.54-150.48a15 15 0 0 1 14.62-18.36h274.27a15 15 0 0 1 14.65 18.36l-34.6 150.48a15 15 0 0 1 -14.62 11.67zm-193.19-30h181.25l27.67-120.48h-236.6z"></path><path d="m222 450a57.48 57.48 0 1 1 57.48-57.48 57.54 57.54 0 0 1 -57.48 57.48zm0-84.95a27.48 27.48 0 1 0 27.48 27.47 27.5 27.5 0 0 0 -27.48-27.47z"></path><path d="m368.42 450a57.48 57.48 0 1 1 57.48-57.48 57.54 57.54 0 0 1 -57.48 57.48zm0-84.95a27.48 27.48 0 1 0 27.48 27.47 27.5 27.5 0 0 0 -27.48-27.47z"></path><path d="m158.08 165.49a15 15 0 0 1 -14.23-10.26l-25.71-77.23h-47.44a15 15 0 1 1 0-30h58.3a15 15 0 0 1 14.23 10.26l29.13 87.49a15 15 0 0 1 -14.23 19.74z"></path></svg>
            </button>
        </div>
    </div>
`

class ClotheCard extends HTMLElement {
    constructor(){
        super();
        this.attachShadow({mode: 'open'})
        this.shadowRoot.appendChild(template.content.cloneNode(true));
    }
    connectedCallback(){
        this.shadowRoot.querySelector('img').src = this.getAttribute('urlImg');
        this.shadowRoot.querySelector('img').alt = this.getAttribute('nombrePrenda');
        this.shadowRoot.querySelector('#nombrePrenda').innerText = this.getAttribute('nombrePrenda');
        this.shadowRoot.querySelector('#descripcionPrenda').innerText = this.getAttribute('descripcionPrenda');
        this.shadowRoot.querySelector('#color').innerText = this.getAttribute('color');
        this.shadowRoot.querySelector('#talla').innerText = this.getAttribute('talla');
        this.shadowRoot.querySelector('#stock').innerText = this.getAttribute('stock')
        this.shadowRoot.querySelector('#precio').innerText = this.getAttribute('precio')
    }
}

customElements.define('clothe-card', ClotheCard);