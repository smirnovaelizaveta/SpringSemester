export interface Project {
  files: ProjectFile[];
}

export interface ProjectFile {
  name: String;
  contents?: String;
  children?: ProjectFile[];
  readonly: Boolean;
}