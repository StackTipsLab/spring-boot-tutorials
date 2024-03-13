import SockJS from 'sockjs-client';
import Stomp from 'webstomp-client';

export default {
    stompClient: null,
    isConnected: false,

    connect() {
        return new Promise((resolve, reject) => {
            const socket = new SockJS('http://localhost:8080/chat');
            this.stompClient = Stomp.over(socket);
            this.stompClient.connect({}, frame => {
                this.isConnected = true;
                resolve(frame);
            }, error => {
                reject(error);
            });
        });
    },

    disconnect() {
        if (this.stompClient !== null) {
            this.stompClient.disconnect();
        }
        this.isConnected = false;
    },

    subscribe(topic, callback) {
        if (!this.isConnected) {
            throw new Error("WebSocket is not connected.");
        }
        this.stompClient.subscribe(topic, message => {
            callback(JSON.parse(message.body));
        });
    },

    sendMessage(destination, message) {
        if (!this.isConnected) {
            throw new Error("WebSocket is not connected.");
        }
        this.stompClient.send(destination, JSON.stringify(message), {});
    }
};
