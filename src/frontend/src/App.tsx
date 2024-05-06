import {z} from 'zod'
import {useForm} from "react-hook-form";
import {zodResolver} from "@hookform/resolvers/zod";
import {Form, FormControl, FormField, FormItem, FormLabel, FormMessage} from "@/components/ui/form";
import {Input} from "@/components/ui/input";
import {Button} from "@/components/ui/button";
import {Select, SelectContent, SelectItem, SelectTrigger, SelectValue} from "@/components/ui/select";
import {useState} from "react";
import {useToast} from "@/components/ui/use-toast";


type response = {
  message: string,
  path?: string[],
  executionTime?: number
  nodeVisited?: number
}

const formSchema = z.object({
  startWord: z.string(),
  endWord: z.string(),
  algorithm: z.enum(["UCS", "AStar", "GBFS"]),
}).refine((data) => {
  return data.startWord.length === data.endWord.length
}, {
  message: "Start word and end word must have the same length",
  path: ["endWord"],
})

function App() {
  const {toast} = useToast()
  const [data, setData] = useState<response | null>(null)

  const form = useForm<z.infer<typeof formSchema>>({
    resolver: zodResolver(formSchema)
  });

  const handleReset = () => {
    setData(null);
  }

  const handleSubmit = (values: z.infer<typeof formSchema>) => {
    // console.log({values})
    toast({
      title: "Solving...",
      description: "Please wait a moment",
      duration: 999999999999 * 9, // maunya sih selamanya
    })
    fetch(`http://localhost:8080/api?StartWord=${values.startWord}&EndWord=${values.endWord}&Method=${values.algorithm}`)
      .then(response => response.json())
      .then(data => {
        // console.log(data)
        if (data.path === undefined) {
          toast({
            title: "Error",
            description: data.message,
            variant: "destructive"
          })
        } else {
          setData(data)
          toast({
            title: "Done",
            description: "Path found!"
          })
        }
      })
      .catch(error => {
        console.error('Error:', error);
        toast({
          title: "Error",
          description: "An error occurred while solving the word ladder",
          variant: "destructive"
        })
      });

  }

  return (
    <>
      <div
        className="from-slate-900 to-slate-800 h-screen font-montserrat bg-gradient-to-b absolute w-full text-slate-200 flex flex-col justify-center items-center">
        <div className="flex flex-col justify-center items-center absolute top-16">
          <h1 className="text-4xl font-bold">Word Ladder Solver</h1>
          <p className="text-sm mt-2 mb-6">made by <a href="https://github.com/satriadhikara" target="_blank"
                                                      className=" cursor-pointer hover:underline transition">@Satriadhikara</a> using <a
            href="https://docs.oracle.com/javase/tutorial/collections/interfaces/examples/dictionary.txt"
            target="_blank"
            className="hover:underline cursor-pointer">Oracle
            dictionary</a></p>

        </div>
        {!data ? (<>
          <Form {...form}>
            <form onSubmit={form.handleSubmit(handleSubmit)}
                  className="max-w-md w-full flex flex-col gap-4">
              <FormField render={({field}) => {
                return <FormItem>
                  <FormLabel>Start word</FormLabel>
                  <FormControl>
                    <Input {...field} placeholder="Start word" type="text" className="text-slate-700"/>
                  </FormControl>
                  <FormMessage/>
                </FormItem>
              }} name="startWord" control={form.control}/>
              <FormField render={({field}) => {
                return <FormItem>
                  <FormLabel>End word</FormLabel>
                  <FormControl>
                    <Input {...field} placeholder="End word" type="text" className="text-slate-700"/>
                  </FormControl>
                  <FormMessage/>
                </FormItem>
              }} name="endWord" control={form.control}/>
              <FormField render={({field}) => {
                return <FormItem>
                  <FormLabel>Algorithm</FormLabel>
                  <Select onValueChange={field.onChange}>
                    <FormControl>
                      <SelectTrigger className="text-slate-700">
                        <SelectValue placeholder="Select an algorithm"/>
                      </SelectTrigger>
                    </FormControl>
                    <SelectContent>
                      <SelectItem value="UCS">Uniform Cost Search</SelectItem>
                      <SelectItem value="AStar">A*</SelectItem>
                      <SelectItem value="GBFS">Greedy Best First Search</SelectItem>
                    </SelectContent>
                  </Select>
                  <FormMessage/>
                </FormItem>
              }} name="algorithm" control={form.control}/>
              <Button type="submit" className="w-full shadow-white mt-2">Solve</Button>
            </form>
          </Form>
        </>) : (<>
          <div className="flex flex-col gap-4 overflow-auto max-h-[350px]">
            {data?.path?.map((word, index) => (
              <div key={index} className="flex gap-3">
                {word.split('').map((letter, letterIndex) => {
                  const endWordLetter = form.getValues().endWord[letterIndex].toUpperCase();
                  const bgColor = letter !== endWordLetter ? 'bg-slate-700' : 'bg-white';
                  const textColor = letter !== endWordLetter ? 'text-white' : 'text-slate-700';
                  return (
                    <p key={letterIndex}
                       className={`rounded h-20  w-16 flex justify-center items-center text-5xl ${bgColor} ${textColor}`}>{letter}</p>
                  );
                })}
              </div>
            ))}
          </div>
          <p className="text-lg mt-7">{data.path ? data.path.length - 1 : null} path in {data.executionTime} ms</p>
          <p>{data.nodeVisited} nodes visited</p>
          <p className="text-lg mt-2 hover:underline cursor-pointer" onClick={handleReset}>Go back</p>

        </>)}
      </div>
    </>
  )
}

export default App
