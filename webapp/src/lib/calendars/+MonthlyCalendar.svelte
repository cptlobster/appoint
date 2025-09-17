<script lang="ts">
    import { add, eachDayOfInterval, format, sub } from "date-fns";
    let { contains = $bindable(), ...props } = $props();
    let month = contains.getUTCMonth();

    function get_calendar_dates(date: Date): Date[] {
        // normalize start of iterator
        let start_dow = date.getDay()
        let start = sub(date, {days: (7 - start_dow) % 7})
        let end = sub(add(start, {weeks: 5}), {days: 1})
        // generate dates to fill calendar
        let dates = eachDayOfInterval({start: start, end: end})
        return dates
    }

    function* chunks<T>(arr: T[], n: number): Generator<T[], void> {
        for (let i = 0; i < arr.length; i += n) {
            yield arr.slice(i, i + n);
        }
    }
</script>

<table>
    <thead>
        <tr>
            {#each ["Sun", "Mon", "Tue", "Wed", "Thu", "Fri", "Sat"] as day}
                <th>{day}</th>
            {/each}
        </tr>
    </thead>
    <tbody>
    {#each [...chunks(get_calendar_dates(contains), 7)] as week}
        <tr>
        {#each week as day}
            <td class:other_month={day.getUTCMonth() != month}>{format(day, "d")}</td>
        {/each}
        </tr>
    {/each}
    </tbody>
</table>

<style>
th, td {
    border: 1px solid hsla(210, 50%, 80%, 20%);
    padding: 0.25rem;
    width: calc(100% / 7);
    text-align: center;
}

table, thead {
    border: 1px solid hsl(210, 50%, 80%);
    min-width: 30rem;
    width: 100%;
}

td.other_month {
    color: var(--text-inactive-color)
}
</style>