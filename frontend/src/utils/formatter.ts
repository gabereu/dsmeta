export const BRL = Intl.NumberFormat('pt-BR', { style: 'currency', currency: 'BRL' });



export const isoOnlyDate = (date?: Date | null) => {
    if(!date) return undefined;

    return date.toISOString().slice(0, 10);
}