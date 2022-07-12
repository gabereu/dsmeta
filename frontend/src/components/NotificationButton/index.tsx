import icon from "../../assets/svg/notification-icon.svg";

import "./styles.css"

function NotificationButton() {
    return (
        <button className="dsmeta-red-btn">
            <img src={icon} alt="" />
        </button>
    )
}

export default NotificationButton;