import NotificationButton from "../NotificationButton";
import DatePicker from "react-datepicker";

import "./styles.css"
import "react-datepicker/dist/react-datepicker.css";
import { useEffect, useState } from "react";
import axios from "axios";
import { request } from "../../utils/axiosConfig";
import { Sale } from "../../models/Sale";
import { BRL, isoOnlyDate } from "../../utils/formatter";


function SalesCard() {

    const [fromDate, setFromDate] = useState<Date | null>(new Date(new Date().setDate(new Date().getDate()-365)));
    const [toDate, setToDate] = useState<Date | null>(new Date());

    const [sales, setSales] = useState<Sale[]>([]);

    useEffect(() => {

        request.get(
            '/sales', 
            { params: { minDate: isoOnlyDate(fromDate), maxDate: isoOnlyDate(toDate) } }
        ).then(
            r => setSales(r.data.content)
        );
    }, [fromDate, toDate]);

    return (
        <div className="dsmeta-card">
            <h2 className="dsmeta-sales-title">Vendas</h2>
            <div>
                <div className="dsmeta-form-control-container">
                    <DatePicker
                        selected={fromDate}
                        onChange={setFromDate}
                        className="dsmeta-form-control"
                        dateFormat="dd/MM/yyyy"
                    />
                </div>
                <div className="dsmeta-form-control-container">
                    <DatePicker
                        selected={toDate}
                        onChange={setToDate}
                        className="dsmeta-form-control"
                        dateFormat="dd/MM/yyyy"
                        minDate={fromDate}
                    />
                </div>
            </div>

            <div>
                <table className="dsmeta-sales-table">
                    <thead>
                        <tr>
                            <th className="show992">ID</th>
                            <th className="show576">Data</th>
                            <th>Vendedor</th>
                            <th className="show992">Visitas</th>
                            <th className="show992">Vendas</th>
                            <th>Total</th>
                            <th>Notificar</th>
                        </tr>
                    </thead>
                    <tbody>
                        { sales.map(sale => 
                            <tr key={sale.id}>
                                <td className="show992">#{sale.id}</td>
                                <td className="show576">{new Date(sale.date).toLocaleDateString()}</td>
                                <td>{sale.sellerName}</td>
                                <td className="show992">{sale.visited}</td>
                                <td className="show992">{sale.deals}</td>
                                <td>{BRL.format(sale.amount)}</td>
                                <td>
                                    <div className="dsmeta-red-btn-container">
                                        <NotificationButton />
                                    </div>
                                </td>
                            </tr>
                        ) }
                    </tbody>

                </table>
            </div>

        </div>
    )
}

export default SalesCard;