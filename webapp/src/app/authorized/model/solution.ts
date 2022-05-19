export interface Solution {
	checked: boolean,
	correct: boolean,
	stackTrace?: string
}

export interface SolutionUpdate extends Solution {
	taskId: number
}