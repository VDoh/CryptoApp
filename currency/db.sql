CREATE TABLE IF NOT EXISTS user(
    id_user integer PRIMARY KEY, 
    user_type integer not null,
    nick text not null, 
    email text not null, 
    password text not null
);

CREATE TABLE IF NOT EXISTS notes(
    id integer PRIMARY KEY, 
    id_user integer,
    title text not null, 
    note_content text,
    date TEXT,
    FOREIGN KEY (id_user) REFERENCES user (id_user)
);

CREATE TABLE IF NOT EXISTS currency_measure(
    id integer PRIMARY KEY,
    id_user integer, 
    ETH real, 
    BTC real, 
    USDT real, 
    XRP real, 
    BCH real, 
    date text,
    FOREIGN KEY (id_user) REFERENCES user (id_user)   
);
CREATE TABLE IF NOT EXISTS vip_application(
    id_app integer PRIMARY key, 
    id_user integer not null, 
    text_content text, 
    date text, 
    status integer, 
    FOREIGN KEY (id_user) REFERENCES user (id_user) 
);